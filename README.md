# Department Calculator

A Coding Challenge in Java

## Project Overview

This project is based on a local company's challenge to employment candidates.  Specific details are described below.

## Problem Statement
Create a small application that can calculate a department and/or manager’s monthly expense
allocation based on the number and types of employees reporting to the manager or contained within
the department.

## Specific Requirements
* Developers warrant an allocation of $1000 each.
* QA Testers warrant an allocation of $500 each.
* Managers warrant an allocation of $300 each.
* Managers can have QA Testers, Developers, and other managers report to them.
* Departments consist of any kinds of employees.
* Users of this application should be able to:
  * Determine the monthly expense allocation warranted a manager who has various employee types reporting to him/her at least two levels deep (Manager -> Manager -> Developer). The level of depth of the hierarchy should be flexible ideally.
  * Determine the monthly expense allocation warranted a department with various employee types under it at multiple levels deep just as the Manager can.

## Example Scenario
* Manager A
  * Manager B
    * Developer
    * QA Tester
* Manager A’s allocation should be: $2100

## Project Details & Constraints
* No user interface is necessary to prove your project solution works. You can prove it works with unit tests following whatever best-practices you deem appropriate.
* The project should be zipped and emailed upon completion.
* You may use any design patterns and frameworks you deem necessary to accomplish this task
