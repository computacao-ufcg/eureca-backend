package br.edu.ufcg.computacao.eureca.backend.core.util;

import br.edu.ufcg.computacao.eureca.backend.constants.Messages;
import br.edu.ufcg.computacao.eureca.common.exceptions.FatalErrorException;

import java.lang.reflect.Constructor;
import java.util.Arrays;

// Each package has to have its own ClassFactory

public class ClassFactory {

    public Object createClass(String className, String ... params) throws FatalErrorException {

        Object classInstance;
        Constructor<?> constructor;

        try {
            Class<?> classpath = Class.forName(className);
            if (params.length > 0) {
                Class<String>[] constructorArgTypes = new Class[params.length];
                Arrays.fill(constructorArgTypes, String.class);
                constructor = classpath.getConstructor(constructorArgTypes);
            } else {
                constructor = classpath.getConstructor();
            }

            classInstance = constructor.newInstance(params);
        } catch (ClassNotFoundException e) {
            throw new FatalErrorException(String.format(Messages.UNABLE_TO_FIND_CLASS_S, className));
        } catch (Exception e) {
            throw new FatalErrorException(e.getMessage(), e);
        }
        return classInstance;
    }
}
