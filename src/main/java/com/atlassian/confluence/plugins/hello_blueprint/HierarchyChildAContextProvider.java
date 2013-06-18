package com.atlassian.confluence.plugins.hello_blueprint;

import com.atlassian.plugin.PluginParseException;
import com.atlassian.plugin.web.ContextProvider;

import java.util.Map;

/**
 * Provides content for the "Hierarchy" Child A Blueprint.
 *
 * @since 1.6
 */
public class HierarchyChildAContextProvider implements ContextProvider
{
    @Override
    public void init(Map<String, String> params) throws PluginParseException
    {
    }

    @Override
    public Map<String, Object> getContextMap(Map<String, Object> context)
    {
        context.put("foo", "bar");

        return context;
    }
}
