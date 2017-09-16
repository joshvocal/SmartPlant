#include <ESP8266WiFi.h>
#include <FirebaseArduino.h>

#define FIREBASE_HOST "smartplant-f36cd.firebaseio.com"
#define FIREBASE_AUTH "KqQu0kosgkpBzpR4cV80aszOfTQcghMDUsFPW31Z"
#define WIFI_SSID "TELUS4928"
#define WIFI_PASSWORD "b7fbfd0e33"

const int ledPin = 12;


void setup() {
  Serial.begin(9600);
  
  pinMode(ledPin, OUTPUT);

  // Connect to Wifi
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);

  Serial.print("connecting");

  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(500);
  }

  Serial.println();
  Serial.print("connected: ");
  Serial.println(WiFi.localIP());

  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);

  //  createJsonObject();
}

//void createJsonObject() {
//
//  StaticJsonBuffer<200> jsonBuffer;
//  JsonObject& root = jsonBuffer.createObject();
//  root["name"] = "Dave";
//  root["type"] = "Rose";
//
//  JsonArray& data = root.createNestedArray("moisture");
//
//  Firebase.push("/", root);
//}

void loop() {

//  setDeviceName();
//  setDeviceType();
  updateMoisture();

  digitalWrite(ledPin, HIGH);   // turn the LED on (HIGH is the voltage level)
  delay(1000);              // wait for a second
  digitalWrite(ledPin, LOW);    // turn the LED off by making the voltage LOW

  delay(1000);
}

void setDeviceName() {

  // Set device name
  Firebase.setString("device/name", "Doug");

  // Handle error
  if (Firebase.failed()) {
    Serial.print("setting device/name failed:");
    Serial.println(Firebase.error());
    return;
  }
}

void setDeviceType() {

  // Set device type
  Firebase.setString("device/type", "Rose");

  // Handle error
  if (Firebase.failed()) {
    Serial.print("setting device/type failed:");
    Serial.println(Firebase.error());
    return;
  }
}

void updateMoisture() {

  // Append new moisture reading to device/moisture
  Firebase.pushInt("moistures", 1024);

  // Handle error
  if (Firebase.failed()) {
    Serial.print("pushing device/moisture failed:");
    Serial.println(Firebase.error());
    return;
  }
}


