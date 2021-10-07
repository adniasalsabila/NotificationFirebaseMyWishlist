<?php

$_GET['function']();

function sendPushNotification($fcm_token, $title, $message, $id = null, $action = null)
{

    $url = "https://fcm.googleapis.com/fcm/send";
    $header = [
        'authorization: key=AAAAYBJIMTQ:APA91bG6x0PHYsN8j_aB5crDuLmiUb8QYv6KJcXKSjjct60Zwz4xUJFMZ106cjs_ty2ZnX9IcuQ44fNw4dir5Y-KT9AoHYSj042N4AzrxxTp-RVMMDSDnS1sVcq_cJN_lVKKNkf07Fdq',
        'content-type: application/json'
    ];

    $notification = [
        'title' => $title,
        'body' => $message
    ];
    $extraNotificationData = ["message" => $notification, "id" => $id, 'action' => $action];

    $fcmNotification = [
        'to'        => $fcm_token,
        'notification' => $notification,
        'data' => $extraNotificationData
    ];

    $ch = curl_init();
    curl_setopt($ch, CURLOPT_URL, $url);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
    curl_setopt($ch, CURLOPT_CONNECTTIMEOUT, 60);
    curl_setopt($ch, CURLOPT_CUSTOMREQUEST, 'POST');
    curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fcmNotification));
    curl_setopt($ch, CURLOPT_HTTPHEADER, $header);

    $result = curl_exec($ch);
    curl_close($ch);

    return $result;
}

function sendNotif()
{
    $title = $_POST['title'] ?: "null";
    $body = $_POST['body'] ?: "null";
    sendPushNotification("cxkZ_Nk8TEuWogScJanAc2:APA91bElKOXCKzbXnHg-ohcOCOVsp2RXOUmAFySpC06xaeIyPE805wl6dUDkw5yr9WkV5AHeIgmJRPd4XH8H_dMqiui7jOfKWHUw-kpwxnJLdlaZkRmAipmsWLW_d_-c9St_0UB4n4zq", $title, $body, 123456, "notif");
}