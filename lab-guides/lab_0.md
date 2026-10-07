# Lab 00: Configure the Techzone Environment

## Overview

This lab walks you through reserving a Techzone VM and installing the required tools — IBM Bob, IBM AMA, and the project repository — so you are ready to begin the subsequent labs. By the end of this lab the **Application Modernization Accelerator (AMA)** UI will be running and accessible inside the VM.

---

## Step 1: Reserve the Techzone VM

1. Go to this [Techzone reservation link](https://techzone.ibm.com/collection/69c6c2951bdc18e8109d08ea?platform=6a68fc27db666641aab7f332) to reserve the VM.

2. Enter any name and description for the reservation, then click **Next**.

   ![Reservation Form](images/lab_0_1.png)

3. Select any purpose and click **Next**.

   ![Purpose](images/lab_0_2.png)

4. Select any deployment region and click **Next**.

   ![Region](images/lab_0_3.png)

5. Leave the **Schedule** section unchanged and click **Next**.

   ![Schedule](images/lab_0_4.png)

6. In the **Configuration** section, click **Customize** to adjust the instance details.

   ![Configuration](images/lab_0_5.png)

7. Set the Compute Profile to **`bx2-8x32 – Balanced, 8 vCPU, 32 GB`**.

   ![Compute Profile](images/lab_0_6.png)

8. Scroll down and enable the **Guacamole VNC access**.

   ![VNC Access](images/lab_0_7.png)

9. Scroll back up, save the configuration, and click **Review**.

   ![Save Configuration](images/lab_0_8.png)

10. Scroll down, accept the terms and conditions, and click **Submit**.

11. The reservation will take a few minutes to become ready.

12. Check your email — you will receive an invitation to join an IBM Cloud account.

---

## Step 2: Install Prerequisites

> **Note:** Open the Guacamole UI in **Firefox**. Use right-click copy/paste actions inside the VM rather than <kbd>Ctrl+C</kbd> / <kbd>Ctrl+V</kbd>, which may not work as expected.

1. Go to the [Guacamole UI](https://vdi.cloud.techzone.ibm.com/guacamole).

2. Expand the **IBM Cloud** dropdown, then expand **RHEL 9 IBM Cloud VPC** and click the **VNC Desktop** link.

   ![Guacamole](images/lab_0_9.png)

3. You should see the desktop UI shown below.

   ![VM Desktop](images/lab_0_10.png)

4. Open Firefox from the Taskbar and navigate to this [Box URL](https://ibm.ent.box.com/s/qjuxjflwjmjhfydplf7yctszfbwgutav) inside the VM.

   ![Box Download Page](images/lab_0_11.png)

5. Click the **Download** icon in the top-right corner.

   ![Download Icon](images/lab_0_12.png)

6. Once the zip file has downloaded, click **Activities** in the top-left corner and open a **Terminal** from the Taskbar.

   ![Open Terminal](images/lab_0_13.png)

7. Run the following commands in the terminal:

   ```bash
   cd Downloads
   ```

   ```bash
   unzip BOB_AMA.zip
   ```

   ![Unzip](images/lab_0_14.png)

8. After the zip is extracted, run:

   ```bash
   cd BOB_AMA
   ```

   ```bash
   bash install.sh
   ```

   ![Install Script](images/lab_0_15.png)

9. This script installs IBM Bob, IBM AMA, and clones the project repository. During AMA installation you will be prompted for input:

   - When asked **"Would you like to auto-configure Application Modernization Accelerator for use by rootless user? (y/n)"**, enter `y`.

   ![AMA Install — Step 1](images/lab_0_16.png)

10. When asked which license to install, select **`1`**.

    ![AMA Install — Step 2](images/lab_0_17.png)

11. Select **`1`** again and accept the License Agreements.

    ![AMA Install — Step 3](images/lab_0_18.png)

12. Select operation **`1`** to install AMA.

    ![AMA Install — Step 4](images/lab_0_19.png)

13. Installation takes approximately **5–10 minutes**. Once complete, a URL for the AMA UI will be printed in the terminal. **Copy this URL** — you will need it in Lab 1.

    ![AMA Install Complete — UI URL](images/lab_0_20.png)

14. Open the URL in Firefox. If a security warning appears, click **Accept the Risk and Continue**.

    ![Security Warning](images/lab_0_21.png)

15. The **Application Modernization Accelerator** UI should now load successfully.

    ![AMA UI](images/lab_0_22.png)

---

*Next: [Lab 1](lab_1.md) — Creating the Migration Bundle with AMA.*
