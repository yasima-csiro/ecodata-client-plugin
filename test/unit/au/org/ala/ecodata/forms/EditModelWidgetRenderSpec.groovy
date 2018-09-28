package au.org.ala.ecodata.forms

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import org.codehaus.groovy.grails.plugins.codecs.HTMLCodec
import spock.lang.Specification
import org.codehaus.groovy.grails.web.json.JSONObject

@TestMixin(GrailsUnitTestMixin)
class EditModelWidgetRenderSpec extends Specification {

    EditModelWidgetRenderer editModelWidgetRenderer
    WidgetRenderContext ctx

    def setup() {
        editModelWidgetRenderer = new EditModelWidgetRenderer()
        mockCodec(HTMLCodec)
    }

    def "the feature view model type should be rendered as a feature tag"() {
        setup:
        ctx = ctxBuilder().model([source:'myFeature', type:'feature']).build()

        when:
        editModelWidgetRenderer.renderFeature(ctx)

        then:
        ctx.writer.toString() == """<feature params="feature:myFeature, config:\$config.getConfig('feature', myFeature)"></feature>"""
    }

    def "the feature view model has a dependency on a global template, and must specifiy this"() {
        setup:
        ctx = ctxBuilder().model([source:'myFeature', type:'feature']).build()

        when:
        editModelWidgetRenderer.renderFeature(ctx)

        then:
        ctx.deferredTemplates.contains('/output/mapInDialogTemplate')
    }

    def "the number data type should include a step attribute to make decimal values valid by default"() {
        setup:
        ctx = ctxBuilder().model([source:'myNumber', type:'number']).build()

        when:
        editModelWidgetRenderer.renderNumber(ctx)

        then:
        TestUtils.compareHtml(ctx.writer, """<input type="number" step="any" data-bind="value:myNumber" class="input-small">""")
    }

    def "the textarea renderer should include the html5 maxlength attribute and a placeholder if a maxSize validation rule is defined"() {
        setup:
        ctx = ctxBuilder().model([source:'myText', type:'textarea']).validationString("required,maxSize[300]").build()

        when:
        editModelWidgetRenderer.renderTextArea(ctx)

        then:
        TestUtils.compareHtml(ctx.writer, """<textarea data-bind="value:${ctx.model.source}" maxlength="300" placeholder="(maximum 300 characters)" data-validation-engine="validate[${ctx.dataModel.validate}]"></textarea>""")
    }

    def "the textarea renderer will append to the placeholder text if it exists and a maxSize validation attribute is supplied"() {
        setup:
        ctx = ctxBuilder().model([source:'myText', type:'textarea', placeholder:'placeholder']).validationString("required,maxSize[300]").build()

        when:
        editModelWidgetRenderer.renderTextArea(ctx)

        then:
        TestUtils.compareHtml(ctx.writer, """<textarea data-bind="value:${ctx.model.source}" maxlength="300" placeholder="placeholder (maximum 300 characters)" data-validation-engine="validate[${ctx.dataModel.validate}]"></textarea>""")
    }

    def "the textarea renderer will include rows and cols attributes if supplied in the model"() {
        setup:
        ctx = ctxBuilder().model([source:'myText', type:'textarea', rows:3, cols:100]).build()

        when:
        editModelWidgetRenderer.renderTextArea(ctx)

        then:
        TestUtils.compareHtml(ctx.writer, """<textarea data-bind="value:${ctx.model.source}" rows="3" cols="100" "></textarea>""")
    }

    def "the textarea renderer will include placeholder text if included in the model"() {
        setup:
        ctx = ctxBuilder().model([source:'myText', type:'textarea', placeholder:'my placeholder']).build()

        when:
        editModelWidgetRenderer.renderTextArea(ctx)

        then:
        TestUtils.compareHtml(ctx.writer, """<textarea data-bind="value:${ctx.model.source}" placeholder="${ctx.model.placeholder}"></textarea>""")
    }

    def "the input renderer should include the html5 maxlength attribute and a placeholder if a maxSize validation rule is defined"() {
        setup:
        ctx = ctxBuilder().model([source:'myText', type:'text']).validationString("required,maxSize[100]").build()

        when:
        editModelWidgetRenderer.renderText(ctx)

        then:

        TestUtils.compareHtml(ctx.writer, """<input type="text" data-bind="value:${ctx.model.source}" class="input-small" maxlength="100" placeholder="(maximum 100 characters)" data-validation-engine="validate[${ctx.dataModel.validate}]"></textarea>""")
    }


    WidgetRenderContextBuilder ctxBuilder() {
        new WidgetRenderContextBuilder()
    }

}