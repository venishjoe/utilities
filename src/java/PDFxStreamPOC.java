/*******************************************************************************
 Copyright (c) 2011 Venish Joe Clarence (http://venishjoe.net)

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

import java.io.IOException;
 
import com.snowtide.PDF;
import com.snowtide.pdf.Document;
import com.snowtide.pdf.OutputTarget;
import com.snowtide.pdf.Page;
import com.snowtide.pdf.RegionOutputTarget;
 
public class PDFxStreamPOC {
	  public static void main (String[] args) throws IOException {
		    String pdfFilePath = "data/itextevatest.pdf";
		    Document pdfts = PDF.open(pdfFilePath);
		    
		    RegionOutputTarget tgt = new RegionOutputTarget();
		    tgt.addRegion(86, 53, 229, 409, "name");
		    tgt.addRegion(40, 570, 120, 16, "address");
		    Page p = pdfts.getPage(0);
		    p.pipe(tgt);
		    pdfts.close();
		    
		    pdfts.close();
		    System.out.println(tgt.getRegionText("name"));
		    System.out.println(tgt.getRegionText("address"));
		  }
}