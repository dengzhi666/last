import com.baizhi.CmfzZhenglApplication;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CmfzZhenglApplication.class})
public class TestPoi {

    @Test
    public void test1() throws IOException {
        //创建excel文件
        Workbook workbook = new HSSFWorkbook();
        //创建一张表
        Sheet sheet = workbook.createSheet();
        //创建行
        Row row = sheet.createRow(0);
        //创建单元格
        Cell cell = row.createCell(0);
        //往单元格里添加数据
        cell.setCellValue("编号");

        File file = new File("E:/testPOI");
        if(!file.exists()){
            file.mkdir();
        }

        workbook.write(new FileOutputStream("E:/testPOI/第一个Excel文件.xls"));
        workbook.close();
    }

}
