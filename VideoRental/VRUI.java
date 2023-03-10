public class VRUI {
	private static final Controller controller = new Controller();

	public static void main(String[] args) {
		VRUI ui = new VRUI() ;

		boolean quit = false ;
		while ( ! quit ) {
			int command = ui.showCommand() ;
			switch ( command ) {
				case 0: quit = true ; break ;
				case 1: controller.listCustomers() ; break ;
				case 2: controller.listVideos() ; break ;
				case 3: controller.registerCustomer() ; break ;
				case 4: controller.registerVideo() ; break ;
				case 5: List<String> info =new ArrayList<String>();
					info = controller.scanInfo();
					Customer foundCustomer = controller.getCustomerByName(info.get(0));
			        Video foundVideo = controller.getVideoByName(info.get(1));
			        controller.rentVideoToCustomer(foundCustomer,foundVideo);
					break ;
				case 6: controller.returnVideo() ; break ;
				case 7: controller.getCustomerReport() ; break;
				case 8: controller.clearRentals() ; break ;
				case -1: controller.init() ; break ;
				default: break ;
			}
		}
		System.out.println("Bye");
	}

	public int showCommand() {
		System.out.println("\nSelect a command !");
		System.out.println("\t 0. Quit");
		System.out.println("\t 1. List customers");
		System.out.println("\t 2. List videos");
		System.out.println("\t 3. Register customer");
		System.out.println("\t 4. Register video");
		System.out.println("\t 5. Rent video");
		System.out.println("\t 6. Return video");
		System.out.println("\t 7. Show customer report");
		System.out.println("\t 8. Show customer and clear rentals");

		int command = Controller.scanner.nextInt() ;

		return command ;

	}
}
