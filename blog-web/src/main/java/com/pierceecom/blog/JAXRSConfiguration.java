package com.pierceecom.blog;

import com.pierceecom.blog.post.BlogPostREST;
import com.pierceecom.general.exceptions.PierceNoResultExceptionMapper;


import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/")
public class JAXRSConfiguration extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> classes = new HashSet<>();

        // REST
        classes.add(HelloPierceResource.class);
        classes.add(BlogPostREST.class);

        // EXCEPTION MAPPER
        classes.add(PierceNoResultExceptionMapper.class);

        return classes;
    }

}
