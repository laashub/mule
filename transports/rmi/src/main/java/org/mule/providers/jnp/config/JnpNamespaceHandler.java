/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.providers.jnp.config;

import org.mule.config.MuleProperties;
import org.mule.config.spring.factories.InboundEndpointFactoryBean;
import org.mule.config.spring.factories.OutboundEndpointFactoryBean;
import org.mule.config.spring.handlers.AbstractMuleNamespaceHandler;
import org.mule.config.spring.parsers.generic.MuleOrphanDefinitionParser;
import org.mule.config.spring.parsers.specific.URIBuilder;
import org.mule.config.spring.parsers.specific.endpoint.TransportEndpointDefinitionParser;
import org.mule.config.spring.parsers.specific.endpoint.TransportGlobalEndpointDefinitionParser;
import org.mule.providers.jnp.JnpConnector;
import org.mule.providers.rmi.RmiConnector;

/**
 * Registers a Bean Definition Parser for handling <code>&lt;jnp:connector&gt;</code> elements.
 *
 */
public class JnpNamespaceHandler extends AbstractMuleNamespaceHandler
{

    public static final String METHOD = MuleProperties.MULE_METHOD_PROPERTY;
    public static final String OBJECT = "object";
    public static final String[] PROPERTIES = new String[]{METHOD, RmiConnector.PROPERTY_SERVICE_METHOD_PARAM_TYPES};
    public static final String[] REQUIRED = new String[]{METHOD, OBJECT, URIBuilder.HOST, URIBuilder.PORT};

    public void init()
    {
        registerBeanDefinitionParser("endpoint", new TransportGlobalEndpointDefinitionParser(JnpConnector.JNP, TransportGlobalEndpointDefinitionParser.PROTOCOL, PROPERTIES, REQUIRED).addAlias(OBJECT, URIBuilder.PATH));
        registerBeanDefinitionParser("inbound-endpoint", new TransportEndpointDefinitionParser(JnpConnector.JNP, TransportGlobalEndpointDefinitionParser.PROTOCOL, InboundEndpointFactoryBean.class, PROPERTIES, REQUIRED).addAlias(OBJECT, URIBuilder.PATH));
        registerBeanDefinitionParser("outbound-endpoint", new TransportEndpointDefinitionParser(JnpConnector.JNP, TransportGlobalEndpointDefinitionParser.PROTOCOL, OutboundEndpointFactoryBean.class, PROPERTIES, REQUIRED).addAlias(OBJECT, URIBuilder.PATH));
        registerBeanDefinitionParser("connector", new MuleOrphanDefinitionParser(JnpConnector.class, true));
    }

}