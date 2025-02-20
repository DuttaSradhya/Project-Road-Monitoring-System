<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Email Notification</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            color: #333333;
            margin: 0;
            padding: 0;
        }
        .email-container {
            background-color: #ffffff;
            width: 100%;
            max-width: 600px;
            margin: 0 auto;
            border: 1px solid #dddddd;
            padding: 20px;
        }
        .header {
            background-color: #eeeeee;
            padding: 10px;
            text-align: center;
            border-bottom: 1px solid #dddddd;
        }
        .content {
            padding: 20px;
        }
        .footer {
            background-color: #eeeeee;
            padding: 10px;
            text-align: center;
            border-top: 1px solid #dddddd;
            font-size: 12px;
            color: #777777;
        }
        h1, h2, h3 {
            color: #333333;
        }
        a {
            color: #333333;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="email-container">
        <div class="header">
            <h1>Road Monitoring System</h1>
        </div>
        <div class="content">
            <h2>Hello,</h2>
            <p>A new report has been submitted. Please check your dashboard to find all the reports.</p>            
        </div>
        <div class="footer">
            <p>&copy; 2024 YRoad Monitoring System. All rights reserved.</p>
        </div>
    </div>
</body>
</html>
