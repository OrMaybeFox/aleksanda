package kursevicius.karolis.dbforms.docgen;

import kursevicius.karolis.dbforms.bean.GenerateRequest;
import kursevicius.karolis.dbforms.bean.GenerateResult;

public interface FileGenerator {
    GenerateResult generate(GenerateRequest generateRequest) throws Exception;
}
