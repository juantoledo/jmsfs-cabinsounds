package piniufly.file;

import org.junit.Test;
import piniufly.service.FileStructureHelper;

import javax.swing.*;

public class FileStructureTest {

    @Test
    public void convertTest() throws Exception {

        FileStructureHelper.convertToUIModel("C:\\cabinsounds\\LATAM", new JFrame());

    }
}
