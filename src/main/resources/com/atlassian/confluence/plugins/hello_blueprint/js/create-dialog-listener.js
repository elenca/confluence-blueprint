(function ($) {

    function validate(dialog) {
        var $dialog = dialog.popup.element,
            $titleField = $dialog.find("#hello-blueprint-page-title");

        if ($.trim($titleField.val()) == "") {
            $titleField.focus().siblings(".error").html(AJS.I18n.getText("confluence.hello.blueprint.form.validation.name.required"));
            return false;
        }

        return true;
    }

    function wizard(createDialog, spaceKey, createFn) {
        var page = createDialog.addPage("hello-blueprint-form-page"),
            createHandler = function (dialog) {
                if (!validate(dialog)) {
                    return;
                }

                createDialog.hide();

                var title = $("#hello-blueprint-form *[name='title']").val();
                var context = {
                    description: $("#hello-blueprint-form *[name='description']").val() || ""
                };
                createFn(title, context);
            };

        page.addHeader(AJS.I18n.getText("confluence.hello.blueprint.dialog.title"))
                .addPanel("SinglePanel", Confluence.Blueprints.Hello.dialogForm(), "singlePanel")
                .addButton("Back", function (dialog) {
                    dialog.prevPage();
                    dialog.page.pop(); // remove the extra dialog page when we move back.
                }, "hello-blueprint-back-button")
                .addButton("Create", createHandler, "hello-blueprint-create-button")
                .addLink("Cancel", function (dialog) {
                    dialog.remove();
                }, "hello-blueprint-cancel-link");

        /* establish tab ordering */
        var $dialog = createDialog.popup.element;
        $dialog.find("#hello-blueprint-page-title").attr("tabindex", 10);
        $dialog.find("#hello-blueprint-page-description").attr("tabindex", 15);
        $dialog.find(".hello-blueprint-create-button").attr("tabindex", 20);
        $dialog.find(".hello-blueprint-back-button").attr("tabindex", 25);
        $dialog.find(".hello-blueprint-cancel-link").attr("tabindex", 30);

        page.getPanel(0).setPadding(0);
        $dialog.find("#hello-blueprint-page-title").focus();

        $dialog.find("#hello-blueprint-form").submit(function () {
            createHandler(createDialog);
            return false;
        });

        return false;
    }

    Confluence.ContentCreator.register('com.atlassian.confluence.plugins.hello-blueprint:hello-blueprint-item', wizard);
})(AJS.$);
