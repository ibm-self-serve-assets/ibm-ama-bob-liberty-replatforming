# Lab 01: Creating the Migration Bundle

> **Prerequisites:** Complete [Lab 00](lab_0.md) to install the required tools and start the AMA UI before proceeding. The AMA UI URL printed at the end of Lab 00 is needed in Step 1 below.

---

## Overview

In this lab you will use the **IBM Application Modernization Accelerator (AMA)** Discovery Tool to analyse the `ModResorts` sample application and generate a migration bundle targeting WebSphere Liberty.

---

## Step 1: Create a Workspace in AMA

1. Navigate to the AMA UI URL printed at the end of Lab 00.

   ![AMA UI](images/lab_1_1.png)

2. Click **Create Workspace**, enter a name of your choice, and click **Create**.

   ![Create Workspace](images/lab_1_2.png)

3. Click **Open Discovery Tool**.

   ![Open Discovery Tool](images/lab_1_3.png)

4. Select **Linux** from the platform dropdown and click **Download Discovery Tool**.

   ![Download Discovery Tool for Linux](images/lab_1_4.png)

5. Wait for the download to complete before continuing.

   ![Download Complete](images/lab_1_5.png)

---

## Step 2: Run the Discovery Tool

6. Open a Terminal by clicking **Activities** in the top-left corner and selecting **Terminal** from the taskbar. Then extract the downloaded archive:

   ```bash
   cd Downloads
   ```

   ```bash
   tar xvfz DiscoveryTool-Linux_Mod_resorts_java.tgz
   ```

   ![Extract Archive](images/lab_1_6.png)

7. Once extraction is complete, navigate into the tool directory and run the analysis against the project `.war` file downloaded during Lab 00:

   ```bash
   cd ama-discovery-5.1.0
   ```

   ```bash
   ./bin/ama-discovery \
     --tomcat-apps-location ~/Downloads/BOB_AMA/ibm-ama-bob-liberty-replatforming/java-liberty-replatforming-tomcat/target/modresorts-2.0.0.war
   ```

8. Read and accept the License Agreement when prompted.

   ![Accept License](images/lab_1_7.png)

9. When asked to select a Java version, choose **option 4 (Java 1.8)**.

   ![Select Java Version](images/lab_1_8.png)

10. Wait for the tool to finish its analysis. It will automatically upload the results to your AMA workspace.

    ![Analysis Complete](images/lab_1_9.png)

---

## Step 3: View the Migration Bundle

11. Return to the AMA UI and open the workspace you created in Step 1.

    ![Open Workspace](images/lab_1_10.png)

12. Select **Liberty** as the target runtime and click **Confirm**.

    ![Select Liberty](images/lab_1_11.png)

13. The workspace should now display the discovered application, as shown below.

    ![Discovered Application](images/lab_1_12.png)

14. Click the **Assessment** tab and scroll down to see your application details. Click the **→** arrow to view the migration plan.

    ![Assessment Tab](images/lab_1_13.png)

15. With a trial licence, only the migration overview is visible. A full licence is required to download the complete migration bundle.

    ![Migration Bundle Preview](images/lab_1_14.png)

---

*Next: [Lab 02](lab_2.md) — Applying the Migration Bundle with IBM Bob.*
