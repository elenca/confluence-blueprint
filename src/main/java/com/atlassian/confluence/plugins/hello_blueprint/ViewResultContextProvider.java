package com.atlassian.confluence.plugins.hello_blueprint;

import com.atlassian.confluence.languages.LocaleManager;
import com.atlassian.confluence.user.AuthenticatedUserThreadLocal;
import com.atlassian.confluence.util.i18n.I18NBean;
import com.atlassian.confluence.util.i18n.I18NBeanFactory;
import com.atlassian.plugin.PluginParseException;
import com.atlassian.plugin.web.ContextProvider;

import java.util.Map;

import static com.atlassian.confluence.plugins.createcontent.actions.DefaultBlueprintContentGenerator.CONTENT_PAGE_TITLE_CONTEXT_KEY;

public class ViewResultContextProvider implements ContextProvider
{
    private final LocaleManager localeManager;
    private final I18NBeanFactory i18NBeanFactory;
    private final HelloService helloService;

    public ViewResultContextProvider(LocaleManager localeManager, I18NBeanFactory i18NBeanFactory, HelloService helloService)
    {
        this.localeManager = localeManager;
        this.i18NBeanFactory = i18NBeanFactory;
        this.helloService = helloService;
    }

    @Override
    public void init(Map<String, String> params) throws PluginParseException
    {
    }

    @Override
    public Map<String, Object> getContextMap(Map<String, Object> context)
    {

        final String pageTitle = i18nBean().getText("confluence.view.result.blueprint.content.title", new String[]{helloService.getFriendlyDateTime()});

        context.put(CONTENT_PAGE_TITLE_CONTEXT_KEY, pageTitle);

        return context;
    }

    private I18NBean i18nBean()
    {
        return i18NBeanFactory.getI18NBean(localeManager.getLocale(AuthenticatedUserThreadLocal.getUser()));
    }
}
