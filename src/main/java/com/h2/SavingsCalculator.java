package com.h2;

import java.time.LocalDate;
import java.time.YearMonth;

public class SavingsCalculator
{
	private float[] credits;
	private float[] debits;

	private float sumOfCredits()
	{
		float sum = 0.0f;
		
		for (float credit: credits)
		{
			sum += credit;
		}

		return sum;
	}
	
	private float sumOfDebits()
	{
		float sum = 0.0f;

		for (float debit: debits)
		{
			sum += debit;
		}

		return sum;
	}

	private static int remainingDaysInMonth(LocalDate date)
	{
		//first get the month and year from date
		int dateYear = date.getYear();
		int dateMonth = date.getMonthValue();

		YearMonth yearMonth = YearMonth.of(dateYear, dateMonth);

		int totalDaysInMonth = yearMonth.lengthOfMonth();
		int remainingDays = totalDaysInMonth - date.getDayOfMonth();

		return remainingDays;
	}

	public float calculate()
	{	
		return sumOfCredits() - sumOfDebits();
	}

	//Constructor
	public SavingsCalculator(float[] credits, float[] debits)
	{
		this.credits = credits;
		this.debits = debits;
	}

	public static void main(String[] args)
	{
		String[] creditsAsString = args[0].split(",");
		String[] debitsAsString = args[1].split(",");

		float[] credits = new float[creditsAsString.length];
		float[] debits = new float[debitsAsString.length];

		//loop through each member of creditAsString and Parse into float
		for (int i = 0; i < creditsAsString.length; i++)
		{
			credits[i] = Float.parseFloat(creditsAsString[i]);
		}

		//same for debits
		for (int i = 0; i < debitsAsString.length; i++)
		{
			debits[i] = Float.parseFloat(debitsAsString[i]);
		}
		
		//Create a new instance of the SavingsCalculator
		SavingsCalculator calculator = new SavingsCalculator(credits, debits);

		
		float netSavings = calculator.calculate();

		System.out.println("Net Savings = " + netSavings + ", remaining days in month = " + remainingDaysInMonth(LocalDate.now()));
	}
}
