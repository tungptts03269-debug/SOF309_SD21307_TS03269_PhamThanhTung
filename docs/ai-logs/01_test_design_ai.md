# AI-Assisted Test Design

## 1. Project Information

- **Project:** PhongKhamOnline
- **Course:** SOF309 – Kiểm thử nâng cao cùng AI
- **Testing scope:** Login and Register functionality
- **AI tool:** ChatGPT
- **Testing framework:** JUnit 5

## 2. Objective

Use AI to support the design of test cases for the Login and Register functionalities.

The objective is to identify normal cases, invalid inputs, boundary conditions, and expected results before implementing automated tests.

## 3. Test Design for Login

| Test case | Input / Condition | Expected result |
|---|---|---|
| LOGIN_01 | Valid username and password | Login succeeds |
| LOGIN_02 | Incorrect password | Login is rejected |
| LOGIN_03 | Empty username | Input is rejected |
| LOGIN_04 | Empty password | Input is rejected |
| LOGIN_05 | Invalid username | Login is rejected |

## 4. Test Design for Register

| Test case | Input / Condition | Expected result |
|---|---|---|
| REGISTER_01 | Valid registration information | Registration succeeds |
| REGISTER_02 | Empty required fields | Input is rejected |
| REGISTER_03 | Invalid email format | Input is rejected |
| REGISTER_04 | Existing username | Registration is rejected |
| REGISTER_05 | Password does not meet requirements | Input is rejected |

## 5. AI Assistance Process

ChatGPT was used to:

- Suggest test scenarios for Login and Register.
- Identify invalid input cases.
- Suggest boundary and validation test cases.
- Help organize test cases into a structured format.
- Support the preparation of JUnit 5 test scripts.

All suggested test cases should be reviewed and adjusted according to the actual application requirements and implementation.

## 6. Expected Benefits

- Improve test coverage.
- Identify potential input validation issues.
- Reduce the time needed to prepare test cases.
- Make test design more systematic.

## 7. Conclusion

AI-assisted test design provides useful suggestions for developing test cases for the PhongKhamOnline project. The proposed cases are used as a reference for implementing and evaluating automated tests.