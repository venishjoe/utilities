import java.io.*;

/*******************************************************************************
 Copyright (c) 2016 Venish Joe Clarence (http://venishjoe.net)

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.

 ******************************************************************************/

public class ReadLineReverse {
    public static String replaceLast(String text, String regex, String replacement) {
        return text.replaceFirst("(?s)"+regex+"(?!.*?"+regex+")", replacement);
    }

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream(new File("D:\\c.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(fis));

        FileWriter fstream = new FileWriter(new File ("D:\\c2.txt"), true);
        BufferedWriter out = new BufferedWriter(fstream);

        String aLine = null;
        String updatedALine = null;
        String updatedALineFinal = null;
        while ((aLine = in.readLine()) != null) {
            updatedALine = replaceLast(aLine, " ", "^");
            updatedALineFinal = updatedALine.replaceFirst(" ","^");
            out.write(updatedALineFinal);
            out.newLine();
        }
        in.close();
        out.close();
    }
}
