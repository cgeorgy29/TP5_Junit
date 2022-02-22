package ticketmachine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@BeforeEach
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de l'initialisation
	// S1 : le prix affiché correspond à l’initialisation
	public void priceIsCorrectlyInitialized() {
		// Paramètres : valeur attendue, valeur effective, message si erreur
		assertEquals(PRICE, machine.getPrice(), "Initialisation incorrecte du prix");
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	public void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
                // Les montants ont été correctement additionnés  
		assertEquals(10 + 20, machine.getBalance(), "La balance n'est pas correctement mise à jour");     
	}
        @Test
	public void ImpPasAssezArgent (){
        //S3 : on n'imprime pas le ticket si le montant inséré est insuffisant
	machine.insertMoney(PRICE - 1);
	assertFalse(machine.printTicket(), "On imprime pas, pas d'€");
	}
        
        @Test
	public void ImpAssezArgent (){
        //S4 : on imprime  le ticket si le montant inséré est suffisant
	machine.insertMoney(PRICE + 1);
	assertTrue(machine.printTicket(), "On imprime, Assez d'€");
	}
        
        @Test
	public void BalanceDec (){
        //S5 : Quand on imprime un ticket la balance est décrémentée du prix du ticket
	machine.insertMoney(PRICE + 1);
        machine.printTicket();
	assertEquals(machine.getBalance(),1, "Balance Changée");
	}
        
         @Test
        //S6 : le montant collecté est mis à jour quand on imprime un ticket (pas avant)
        public void ColApImp() {
            machine.insertMoney(PRICE + 1);
            machine.printTicket();
            assertEquals(machine.getTotal(), PRICE, "Total à jour après ticket");
        }
        @Test
	public void RendArgent (){
        //S7/8 : refund rend la monnaie et remet balance à 0
        machine.insertMoney(PRICE + 1);
        machine.printTicket();
        machine.refund();
	assertEquals(machine.getBalance(),0, "monnaie rendu et balance à 0");
        }
        @Test
	// S9 : On ne peut pas insérer un montant négatif 
	public void MontantNeg() {
            if(PRICE<0){
            assertThrows(IllegalArgumentException.class,()->{ machine.insertMoney(PRICE);});
            }
        }

	@Test 
	// S10 : On ne peut pas créer de machine qui délivre des tickets dont le prix est négatif 
	public void TicketNeg() {
            if (PRICE<0){
            assertThrows(IllegalArgumentException.class,()->{TicketMachine m = new TicketMachine(PRICE);});
            }
        }
}

