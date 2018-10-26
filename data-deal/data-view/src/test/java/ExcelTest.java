import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class ExcelTest {
    @Test
    public void test1(){
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream("E:\\股票代码.xlsx"));
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rsRows = sheet.getPhysicalNumberOfRows();// 行
            XSSFRow hssfRow = sheet.getRow(0);//得到一行，进而下一步得到列数
            int rsColumns = hssfRow.getPhysicalNumberOfCells();// 列
            Map<String,String> map = new HashMap<>();
            String codeType = "";
            for (int j = 0; j < rsColumns; j++)
            {
                if(j==0)    codeType = "sz";    else    codeType = "sh";
                for (int i = 1; i < rsRows; i++)
                {
                    XSSFCell c1 = sheet.getRow(i).getCell(j);// 获取第i行数据的第j列
                    if(c1!=null){
                        String key = c1.getStringCellValue();
                        String value = codeType + key.substring(key.indexOf("(")+1,key.length()-1);
                        map.put(key,value);
                        System.out.println(value);
                    }

                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
