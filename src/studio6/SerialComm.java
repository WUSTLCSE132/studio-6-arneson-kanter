package studio6;

import jssc.*;

public class SerialComm {

	SerialPort port;

	private boolean debug;  // Indicator of "debugging mode"
	
	// This function can be called to enable or disable "debugging mode"
	void setDebug(boolean mode) {
		debug = mode;
	}	
	

	// Constructor for the SerialComm class
	public SerialComm(String name) throws SerialPortException {
		port = new SerialPort(name);		
		port.openPort();
		port.setParams(SerialPort.BAUDRATE_9600,
			SerialPort.DATABITS_8,
			SerialPort.STOPBITS_1,
			SerialPort.PARITY_NONE);
		
		debug = false; // Default is to NOT be in debug mode
	}
		
	// TODO: Add writeByte() method from Studio 5
	public void writeByte(byte b) throws SerialPortException {
		port.writeByte(b);
	}
	
	// TODO: Add available() method
	public boolean available() throws SerialPortException {
		return port.getInputBufferBytesCount() > 0;
	}
	// TODO: Add readByte() method	
	public byte readByte() throws SerialPortException {
		return port.readBytes(1)[0];
	}
	
	public long readBytes(int n) throws SerialPortException {
		long ans = 0;
		
		for (int i = 0; i < n; i++) {
			ans += (0xFF & readByte()) << (8*(n - i - 1));
		}
		return ans;
	}
	
	// TODO: Add a main() method
	public static void main(String[] args) throws SerialPortException {
		SerialComm sc = new SerialComm("/dev/ttyUSB2");
		sc.setDebug(true);
		while (true)
			if (sc.available())
				//System.out.print(sc.debug ? String.format("%02x", sc.readByte())+"\n" : (char)sc.readByte());
				System.out.println(sc.readBytes(4));
	}
}
