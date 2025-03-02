package io.quarkus.arc.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

import jakarta.interceptor.InvocationContext;

public final class InvocationContexts {

    private InvocationContexts() {
    }

    /**
     *
     * @param target
     * @param args
     * @param metadata
     * @return the return value
     * @throws Exception
     */
    public static Object performAroundInvoke(Object target, Object[] args, InterceptedMethodMetadata metadata)
            throws Exception {
        return AroundInvokeInvocationContext.perform(target, args, metadata);
    }

    /**
     *
     * @param target
     * @param chain
     * @param interceptorBindings
     * @return a new invocation context
     */
    public static InvocationContext postConstruct(Object target, List<InterceptorInvocation> chain,
            Set<Annotation> interceptorBindings) {
        return new LifecycleCallbackInvocationContext(target, null, interceptorBindings, chain);
    }

    /**
     *
     * @param target
     * @param chain
     * @param interceptorBindings
     * @return a new invocation context
     */
    public static InvocationContext preDestroy(Object target, List<InterceptorInvocation> chain,
            Set<Annotation> interceptorBindings) {
        return new LifecycleCallbackInvocationContext(target, null, interceptorBindings, chain);
    }

    /**
     *
     * @param target
     * @param chain
     * @param interceptorBindings
     * @return a new {@link jakarta.interceptor.AroundConstruct} invocation context
     */
    public static InvocationContext aroundConstruct(Constructor<?> constructor,
            Object[] parameters,
            List<InterceptorInvocation> chain,
            Supplier<Object> aroundConstructForward,
            Set<Annotation> interceptorBindings) {
        return new AroundConstructInvocationContext(constructor, parameters, interceptorBindings, chain,
                aroundConstructForward);
    }

}
