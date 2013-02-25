package com.atlassian.confluence.plugins.hello_blueprint;

import com.atlassian.plugin.PluginParseException;
import com.atlassian.plugin.web.ContextProvider;

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
        context.put("friendlyDate", helloService.getFriendlyDate());
        // We're adding a string with HTML tags. It will be correctly rendered by our template
        // (check var declaration at content-template.xml)
        context.put("xhtmlString", "<em>Hello I am emphasised</em>");

        return context;
    }
}
