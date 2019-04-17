package kursevicius.karolis.dbforms.docgen;

import kursevicius.karolis.dbforms.bean.GenerateRequest;
import kursevicius.karolis.dbforms.bean.GenerateResult;
import lombok.Value;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.util.Map;

@Value
@Component
public class FileGeneratorPdf implements FileGenerator {
    TemplateEngine templateEngine;

    @Override
    public GenerateResult generate(GenerateRequest generateRequest) throws Exception {
        Context context = new Context();
        generateRequest.getData().entrySet().forEach(stringToObject -> context.setVariable(((Map.Entry) stringToObject).getKey().toString(), stringToObject.getValue()));
        String processedHtml = templateEngine.process(generateRequest.getTemplateName(), context);
        ByteArrayOutputStream boas = createPdf(processedHtml);
        return new GenerateResult(boas.toByteArray());
    }

    private ByteArrayOutputStream createPdf(String processedHtml) throws Exception {
        ByteArrayOutputStream boas = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(processedHtml);
        renderer.layout();
        renderer.createPDF(boas, false);
        renderer.finishPDF();
        return boas;
    }
}
