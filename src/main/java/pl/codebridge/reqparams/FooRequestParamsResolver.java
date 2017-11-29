package pl.codebridge.reqparams;

import java.util.Optional;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class FooRequestParamsResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(FooRequestParams.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
            Integer limit = getIntegerParam("limit", parameter, webRequest);
            Integer offset = getIntegerParam("offset", parameter, webRequest);
            String sortAsc = webRequest.getParameter("sort_asc");
            String sortDesc = webRequest.getParameter("sort_desc");
            FooRequestParams fooRequestParams = new FooRequestParams(limit, offset, sortAsc, sortDesc);
            validate(fooRequestParams, parameter, webRequest, binderFactory);
            return fooRequestParams;
    }

    private static void validate(FooRequestParams fooRequestParams, MethodParameter parameter,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        WebDataBinder binder = binderFactory.createBinder(webRequest, fooRequestParams, "fooRequestParams");
        binder.validate();
        if (binder.getBindingResult().hasErrors()) {
            throw new MethodArgumentNotValidException(parameter, binder.getBindingResult());
        }
    }

    private static Integer getIntegerParam(String name, MethodParameter parameter, NativeWebRequest webRequest) {
        String value = webRequest.getParameter(name);
        try {
            return Optional.ofNullable(value).map(Integer::valueOf).orElse(null);
        } catch (NumberFormatException ex) {
            throw new MethodArgumentTypeMismatchException(value, Integer.class, name, parameter, ex);
        }
    }

}
