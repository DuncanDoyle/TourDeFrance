package org.cdisource.springintegration;

import java.lang.reflect.Type;
import java.util.Set;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.deploy.spi.exceptions.BeanNotFoundException;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

public class CdiFactoryBean implements FactoryBean<Object>, InitializingBean {

    private boolean singleton = true;
    private BeanManager beanManager; // <1>
    private Class<?> beanClass; // <2>
    
    /**
     * BeanContainer is a custom external class which is supposed to make manual Bean lookups from CDI easier. Gonna do it by hand for now.
     */
    //private BeanContainer beanContainer; // <3>
    

    @Override
    public void afterPropertiesSet() throws Exception {
            //beanContainer = new BeanContainerImpl(beanManager);
    }

    public void setBeanClass(Class<?> beanClass) {
            this.beanClass = beanClass;
    }

    @Override
    public Object getObject() throws Exception {
    	Class<?> beanType = getObjectType();
    	Set<Bean<?>> beans = beanManager.getBeans(beanType);
		if (beans.isEmpty()) {
			throw new BeanNotFoundException("Could not locate a bean of type "
					+ beanType.getName());
		}
		Bean<?> bean = beanManager.resolve(beans);
		CreationalContext<?> context = beanManager
				.createCreationalContext(bean);
		@SuppressWarnings("unchecked")
		Object result = beanManager.getReference(bean, bean.getBeanClass(),
				context);
		return result;
    	
    }
        
    @Override
    public Class<?> getObjectType() {
            return beanClass;
    }

    @Override
    public boolean isSingleton() {
            return singleton;
    }

    public void setSingleton(boolean singleton) {
            this.singleton = singleton;
    }

    public void setBeanManager(BeanManager beanManager) {
            this.beanManager = beanManager;
    }

}