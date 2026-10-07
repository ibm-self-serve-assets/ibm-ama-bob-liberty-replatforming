## Liberty Replatforming Lab with Bob

**This is a script for a [video demo](https://youtu.be/7tEBpSLgyEA?si=RImEoI8JlUUEcGyj) on YouTube**

Transitioning Java applications to modern platforms presents unique challenges. Bob's Liberty Replatforming solution transforms this complex process into a streamlined journey by intelligently analyzing your application and creating optimized migration paths to Liberty. Bob adapts precisely to your specific requirements.

This step-by-step approach for Bob will get your application smoothly working on Liberty.

### Demo Overview

In this Demo, you will learn how to:
 - Replatform a simple ModResorts application to use Liberty
 - Use Bob for this type of modernization
 - Deploy the application locally to validate the changes

### Prerequisites

Pre-configuration steps:
 - Clone the test repo
 - Run the repo build, ensure it builds successfully. (The instructions are provides in the README.md)

### Workflow

- You can start the Liberty replatforming journey by creating a new modernizationt task. In Bob chat, you can see a workflow called 'Java modernization'. This workflow launches Bob's comprehensive analysis of the application, identifying compatibility requirements and preparing the optimal migration path to Liberty.

- On clicking the 'Java modernization' workflow, Bob starts the workflow. There are 3 parts to this workflow: 'Analyze - Upgrade - Validate'

- In 'Analyze', Bob performs a Prerequisite check, this step will check if your workspace has all required tools and configurations. Bob will also check your project structure and tailors the experience to your specific needs. After analyzing, Bob comes up with 2 recommendations - 1) Liberty Replatforming, 2) Java Upgrade. In this demo, we are selecting Liberty Replatforming.

- The Liberty Replatforming will initiate the transforming process. For this step, you need to provide your AMA (Application Modernization Accelerator) deployment plan ZIP file. In this repo, the `migration_bundle` directory contains a migration bundle for ModResorts created by [IBM Transformation Advisor](https://www.ibm.com/products/cloud-pak-for-applications/transformation-advisor). It contains an analysis of the application and artifacts that accelerate modernization to Liberty and cloud migration. Leave the Git Flow on to enable automatic creation of a new branch and commits for the selected modernization.

- Automated Upgrade: Bob supports both Java version upgrades (e.g., Java 8 to 21) and server migrations. After initiating the Liberty Replatforming process, Bob conducts a thorough analysis of the application architecture and automatically identifies compatibility requirements and configuration needs. During this migration phase, Bob works to transform your application for Liberty, handling complex technical details while keeping you in control. You can monitor the progress in real-time and provide guidance whenever needed.

- To start the migration process, click "Run Recipes" button to run OpenRewrite recipes to upgrade your Java code for Liberty. Static recipes automate repetitive migration tasks. Bob uses OpenRewrite technology for safe code transformations. The recipe system handles common patterns like API changes and configuration updates.

- Agent-based Manual Changes: Kick off Bob to handle the leftover migrations. Show how Bob find and resolves issues after static migration recipes have been applied. Not all migrations can be resolved by migration recipes. Bob helps to understand, plan, and iteratively resolve the leftover issues.

- Validation and Summary: Review modernization report mermaid diagram. Build the modernized application and review the summary posted by Bob. Bob streamlines the deployment process to Liberty. Validation ensures the modernized application works as expected.

- Deploy the application: Visit http://localhost:9080/resorts/ to view the deployed application.