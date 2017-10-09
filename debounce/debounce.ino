unsigned long lastTime = millis();

void setup(){
  Serial.begin(9600);
  pinMode(2, INPUT);
  attachInterrupt(0, pin_ISR, RISING);
  digitalWrite(2, HIGH);
}

void loop(){
  
}

void pin_ISR(){
  unsigned long tiempo = millis();
  if(tiempo - lastTime >= 100){
    Serial.println("Triggered");
    lastTime = tiempo;
  }
}

