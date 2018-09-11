AJS.bind("blueprint.wizard-register.ready", function () {
    function submitExampleSpace(e, state) {
        state.pageData.ContentPageTitle = state.pageData.name + " " + AJS.I18n.getText("confluence.hello.space.blueprint.home.title.suffix");
        return Confluence.SpaceBlueprint.CommonWizardBindings.submit(e, state);
    }
    function preRenderExampleSpace(e, state) {
        state.soyRenderContext['atlToken'] = AJS.Meta.get('atl-token');
        state.soyRenderContext['showSpacePermission'] = false;
    }
    // Register wizard hooks
    Confluence.Blueprint.setWizard('com.atlassian.confluence.plugins.hello-blueprint:hello-space-blueprint-item', function(wizard) {
        wizard.on("submit.exampleSpaceId", submitExampleSpace);
        wizard.on("pre-render.exampleSpaceId", preRenderExampleSpace);
        wizard.on("post-render.exampleSpaceId", Confluence.SpaceBlueprint.CommonWizardBindings.postRender);
    });
});