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

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import java.util.List;

public class JVMAttach {
    public static void main (String args[]) throws Exception {
        List<VirtualMachineDescriptor> vmList = VirtualMachine.list();
        for(VirtualMachineDescriptor vm : vmList){
            System.out.println("name: " + vm.displayName() + " id :" + vm.id());
            try{
                VirtualMachine vm0 = VirtualMachine.attach(vm.id());
                // load agent, agnet class's agentmain will be invoked.
                //vm0.loadAgent("/root/tmp/ChinaAgent.jar");

                System.out.println("Load agent done.");
                vm0.detach();
            }catch(Exception e) {
                System.out.println("exception : " + e.getMessage());
            }
        }
    }
}
