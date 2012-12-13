package com.atlassian.confluence.plugins.hello_blueprint;

import com.atlassian.plugin.PluginParseException;
import com.atlassian.plugin.web.ContextProvider;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * This provider adds dynamic data for substitution into the content-template.xml \<at:var> elements.
 *
 * For the sake of illustration, the provider in turn gets its data from an injected service.
 */
public class HelloContextProvider implements ContextProvider
{
    private final HelloService helloService;

    public HelloContextProvider(HelloService helloService)
    {
        this.helloService = helloService;
    }

    @Override
    public void init(Map<String, String> params) throws PluginParseException
    {
    }

    @Override
    public Map<String, Object> getContextMap(Map<String, Object> context)
    {
        // Due to a bug, the context is immutable so you have to declare a new Map. This will be fixed in the next
        // milestone.
        Map<String, Object> result = Maps.newHashMap();

        result.put("friendlyDate", helloService.getFriendlyDate());

        return result;
    }
}
