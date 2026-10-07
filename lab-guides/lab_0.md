# Lab 0 : Configure the Techzone environment

## Step 1 : Reverse Techzone VM
1. Go to this [link](https://techzone.ibm.com/collection/69c6c2951bdc18e8109d08ea?platform=6a68fc27db666641aab7f332) to reserve the Techzone VM.
2. Enter any name and description for the reservation and click Next.
![Reservation Form](images/lab_0_1.png)
3. Select any purpose and click Next.
![Purpose](images/lab_0_2.png)
4. Select any deployment region and click Next.
![Region](images/lab_0_3.png)
5. Don't change anything in the Shedule section and click Next
![Schedule](images/lab_0_4.png)
6. In the configuration section we need to customize the instance details. Click on customize.
![Configuration](images/lab_0_5.png)
7. Select the Compute Profile as "bx2-8x32 - Balanced, 8 vCPU, 32GB".
![Compute Profile](images/lab_0_6.png)
8. Scroll down and Enable the Gucamole VNC access.
![VNC access](images/lab_0_7.png)
9. Scroll up and save the configuration and click on review.
![Save](images/lab_0_8.png)
10. Scroll down and accept the terms and conditions and click Submit.
11. It will few minutes for the reservation to be ready.
12. Check your email, you will recieve an invitation to join an IBM cloud account.

## Step 2 : Install Pre-requisties

>Note : Open the VM UI in Chrome, Firefox has some copy paste issues in the VM. Use right click copy paste actions instead of ctrl+C/ctrl+V.

1. Go to the [Gucamole UI](https://vdi.cloud.techzone.ibm.com/guacamole).
2. Expand the IBM Cloud Dropdown, then expand the RHEL 9 IBM Cloud VPC and click on the VNC Desktop link.
![Gucamole](images/lab_0_9.png)
3. You should see the UI shown below.
![VM UI](images/lab_0_10.png)
4. Open Firefox from the Taskbar and go to this [box url](https://ibm.ent.box.com/s/qjuxjflwjmjhfydplf7yctszfbwgutav) in the VM.
![Box](images/lab_0_11.png)
5. Click on the Download icon on top right corner.
![Download](images/lab_0_12.png)
6. Once the zip file is downloaded. Click on Activites on top left corner and then open Terminal from Taskbar.
![Terminal](images/lab_0_13.png)
7. Run the following commands in the Terminal.
```bash
cd Downloads
```
```bash
unzip BOB_AMA.zip
```
![Unzip](images/lab_0_14.png)
8. Once the zip file we downloaded is extracted. Run the following commands
```bash
cd BOB_AMA
```
```bash
bash install.sh
```
![Install](images/lab_0_15.png)
9. This command with install IBM BOB, IBM AMA and clone the git repo with project files. During the AMA installation it will ask for some inputs. Input "y" when asked "Would you like to auto-configure Application Modernization Accelerator for use bt rootless user? (y/n)".
![AMA_Install_1](images/lab_0_16.png)
10. Select 1. when asked which license do you want to install.
![AMA_Install_2](images/lab_0_17.png)
11. Select 1. and accept the License Agreements.
![AMA_Install_3](images/lab_0_18.png)
12. Select Operation 1. to install AMA.
![AMA_Install_4](images/lab_0_19.png)
13. The installation should take 5-10mins. Once installtion is done you will see an url to open the UI.
![AMA_Install_5](images/lab_0_20.png)
14. Copy the URL and open it in firefox. It might show a security warning but click on Accept the Risk and Continue.
![Gucamole](images/lab_0_21.png)
15. The Application Modernization Accelerator UI should load up.
![VM UI](images/lab_0_22.png)
