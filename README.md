# Coupon Controller

This Kotlin controller handles coupon-related operations.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
- [Usage](#usage)
- [Endpoints](#endpoints)
- [Contributing](#contributing)
- [License](#license)
- [Acknowledgments](#acknowledgments)

## Introduction

This Kotlin controller is part of the coupon-related functionalities in the project. It interacts with the Coupon Service to handle the creation, validation, and application of coupon codes.

## Features

- Create coupon configuration
- Check the validity of a coupon code for a user
- Apply a coupon code for a user

## Getting Started

Follow these instructions to set up and run the project locally.

### Prerequisites

- [List any prerequisites or dependencies required to run the project]

### Installation

1. Clone the repository.
2. Install dependencies by running `./gradlew build`.

## Usage

Explain how to use the controller, provide examples, and describe the expected behavior.

## Endpoints

### 1. Create Coupon Configuration

- **Endpoint**: `POST /configuration`
- **Description**: Creates a new coupon configuration.
- **Request Body**: `CreateConfigurationRequest`
- **Response**: Success message or error details.

### 2. Check Coupon Validity

- **Endpoint**: `GET /isValid`
- **Description**: Checks the validity of a coupon code for a user.
- **Query Parameters**:
    - `couponCode`: The coupon code to validate.
    - `userId`: The user ID for whom the coupon is being validated.
- **Response**: Validity status.

### 3. Apply Coupon Code

- **Endpoint**: `POST /apply-coupon`
- **Description**: Applies a coupon code for a user.
- **Request Body**: `ApplyCouponCodeRequest`
- **Response**: Success message or error details.

## Contributing

Contributions are welcome! Please follow the [contribution guidelines](CONTRIBUTING.md).

## License

This project is licensed under the [License Name] - see the [LICENSE.md](LICENSE.md) file for details.

## Acknowledgments

- Mention any contributors or libraries that you'd like to acknowledge.
