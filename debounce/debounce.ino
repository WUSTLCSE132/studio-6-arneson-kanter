unsigned long lastTime = millis();
int count = 0;
unsigned long printTime = millis();


void setup(){
  Serial.begin(9600);
  pinMode(2, INPUT);
  attachInterrupt(0, pin_ISR, RISING);
  digitalWrite(2, HIGH);
}

void loop(){
  unsigned long temp = millis();
  if(temp - printTime >= 1000){
    Serial.println(count);
    printTime = temp;
  }
}

void pin_ISR(){
  unsigned long tiempo = millis();
  if(tiempo - lastTime >= 150){
    count++;
    lastTime = tiempo;
  }
}

