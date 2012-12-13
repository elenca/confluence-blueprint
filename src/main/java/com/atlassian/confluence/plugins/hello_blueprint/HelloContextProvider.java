package com.atlassian.confluence.plugins.hello_blueprint;

import com.atlassian.plugin.PluginParseException;
import com.atlassian.plugin.web.ContextProvider;

import java.util.HashMap;
import java.util.Map;

public class HelloContextProvider implements ContextProvider
{
    @Override
    public void init(Map<String, String> params) throws PluginParseException
    {
    }

    @Override
    public Map<String, Object> getContextMap(Map<String, Object> context)
    {
        Map<String, Object> result = new HashMap<String, Object>();

        result.put("name", "Sherlock");

        return result;
    }
}
