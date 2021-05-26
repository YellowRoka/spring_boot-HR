package hu.webuni.hr.roka;

public enum Grade {
		junior(0),
		medior(1),
		senior(2),
		manager(3),
		ceo(4);

		private final int value;
		
		Grade(int value) {
			this.value = value;
		}
		
		public int getValue(){
			return value;
		}
}
