import com.h2.*;
import java.util.Map;
import java.util.Arrays;
public class Finance
{
	public final static String BEST_LOAN_RATES = "bestLoanRates";
	public final static String SAVINGS_CALCULATOR = "savingsCalculator";
	public final static String MORTGAGE_CALCULATOR = "mortgageCalculator";
	
	//command usage helper
	public final static Map<String, String> commandsToUsage = Map.of
	(
		BEST_LOAN_RATES, "usage: bestLoanRates",
		SAVINGS_CALCULATOR, "usage: savingsCalculator <credits separated by ','> <debits separated by ','>",
		MORTGAGE_CALCULATOR, "usage: mortgageCalculator <loanAmount> <termInYears> <annualRate>"
	);

	private static boolean validateCommandArguments(String[] args)
	{
		switch(args[0])
		{
			case BEST_LOAN_RATES:
				return args.length == 1;
			case SAVINGS_CALCULATOR:
				return args.length == 3;
			case MORTGAGE_CALCULATOR:
				return args.length == 4;
		}
		
		//if there is an incorrect number of arguments, return false
		return false;
	}
	
	private static void executeCommand(String command, String[] arguments)
	{
		
		switch(command)
		{
			case BEST_LOAN_RATES:
				System.out.println("Finding best loan rates ...");
				BestLoanRates.main(arguments);
				break;
			case SAVINGS_CALCULATOR:
				System.out.println("Finding your net savings ...");
				SavingsCalculator.main(arguments);
				break;
			case MORTGAGE_CALCULATOR:
				System.out.println("Finding your monthly payment ...");
				MortgageCalculator.main(arguments);
				break;
		}

		return;
	}

	public static void main(String[] args)
	{
		String command = args[0];

		if (!(commandsToUsage.containsKey(command)))
		{
			System.out.println(command + ": command not found");
			return;
		}
		
		boolean isValidCommand = validateCommandArguments(args);

		if (!(isValidCommand))
		{
			System.out.println(commandsToUsage.get(args[0]));
			
			return;
		}

		String[] arguments = Arrays.copyOfRange(args, 1, args.length);
		executeCommand(command, arguments);

	}
}
