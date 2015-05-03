var main = {
    handlebarsView : function(from, to, context) {
        var source = $(from).html();
        var template = Handlebars.compile(source);
        $(to).append(template(context));
    }
};