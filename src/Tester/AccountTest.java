package Tester;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Account.Account;

public class AccountTest {

	private Account acc = null;

	@BeforeEach
	public void init() {
		//Diese Methode wird VOR JEDEM Test betreten
		//Vor jedem Test ist also ein Konto mit Kontostand 100 vorhanden
		acc = new Account(100);
	}

	@Test
	public void testAccountSimple() {
		//Diese Überprüfung müsste in der Aufgabenstellung genauer definiert sein.
		//Wenn z.B. vorausgesetzt wird, dass beim Erstellen des Objekts negative Werte nicht
		//gesetzt werden (was aktuell nicht berücksichtigt wird), könnte ein Test dazu wiefolgt aussehen:
		Account account = new Account(-0.01);
		assertEquals(0, account.getBalance(), 0.001);
	}
	
	@Test
	public void testGetBalance() {
		//assertTrue(this.acc.getBalance() == 100);
		//double Werte besser mit assertEquals vergleichen:
		//assertEquals(<erwarteter Wert>, <zu vergleichender Wert>, <Toleranz>);
		assertEquals(100, this.acc.getBalance(), 0.001); // 0.001 = Toleranz zwischen Wert 1 und Wert 2
	}
	
	@Test
	public void testWithdrawSuccess() {
		boolean chk = acc.withdraw(0); // 0 abheben
		assertTrue(chk); //Rückgabewert soll true sein
		assertEquals(100, acc.getBalance(), 0.001); // Kontostand immer noch 100
		
		chk = acc.withdraw(100); // alles abheben
		assertTrue(chk); //Rückgabewert true
		assertEquals(0, acc.getBalance(), 0.001); //kein Geld mehr auf dem Konto
	}
	
	@Test
	public void testWithdrawFail() {
		boolean chk = acc.withdraw(100.01); //Mehr abheben, wie Geld auf dem Konto ist
		assertTrue(!chk); //Rückgabewert muss false sein
		//assertFalse(chk);
		assertEquals(100, acc.getBalance(), 0.001); //Kontostand unverändert auf 100
	}

	@Test
	public void testWithdrawNeg() { // Negative Abhebung, nicht berücksichtigt in der Klasse AccountSimple
									// (Fehler in der Klasse Account selbst, den Sie nicht korrigieren müssen)
									// Wenn definiert wird, dass das nicht gehen darf, muss der Kontostand
									// unverändert sein
		boolean chk = acc.withdraw(-0.01);
		assertFalse(chk); // chk muss false sein, damit Test erfolgreich ist
		assertEquals(100, acc.getBalance(), 0.001); // Kontostand unverändert
	}

	@Test
	public void testDepositSuccess() {
		acc.deposit(0); // 0 einzahlen = keine Veränderung
		assertEquals(100, acc.getBalance(), 0.001); // Kontostand bleibt gleich
		acc.deposit(0.01); // 1 Rappen einzahlen
		assertEquals(100.01, acc.getBalance(), 0.001); // neuer Kontostand um 1 Rappen höher
	}

	@Test
	public void testDepositNeg() { // Negative Einzahlung, nicht berücksichtigt in der Klasse AccountSimple
								   // (Fehler in der Klasse Account selbst, den Sie nicht korrigieren müssen)
								   // wenn definiert wird, dass das nicht gehen darf, muss der Kontostand
								   // unverändert 100 sein
		acc.deposit(-0.01);
		assertEquals(100, acc.getBalance(), 0.001);

	}
}