
import java.util.*;
//A tool to help anyone and everyone maintain their body and keep it healthy
/* On inputting name, waist size, hip size, weight, height, age, and gender
 * 1 - Calculates BMI
 * 2 - Calculates BMR
 * 3 - Calculates Calorie requirement
 * 4 - Are you at Risk to heart disease/diabetes?
 * 5 - How much should you excercise?
 * 6 - Provides user with a help menu
 */
class Health_Monitor
{
	public static void main(String args[])

	   {
	     Health_Monitor h = new Health_Monitor();
	     h.menu();
	   }
    static double weight=0.0, height=0.0, waist=0.0, hip=0.0;
    static double bmi=0.0, bmr=0.0;
    static int age=0;
    int opt;
    static int runCount=0;
    static String name;
    static char sex;
    char cho;
    static char excAmt;
    static boolean met=false;
    //Table of values of ideal BMI's for boys and girls of different ages
    double boy[][]={{15,18,19.5},{14.5,17.5,18.5},{14,17,18},{13.75,16.75,18},{13.75,17,18.5},{13.5,17.5,19},{13.75,18,20},{14,18.5,21},{14.25,19.5,22},{14.5,20,23.25},{15,21,24.25},{15.5,22,25},{16,22.5,26},{16.5,23.5,26.75},{17,24,27.5},{17.5,25,28.25},{18.25,25.5,29},{18.75,26.5,29.75},{19,27,30.5}};
    double girl[][]={{14.5,18,19},{14,17.25,18.25},{13.75,16.75,18},{13.5,16.75,18.25},{13.5,17,18.75},{13.5,17.5,19.75},{13.75,18.25,20.75},{13.75,19,21.75},{14,20,23},{14.5,21,24},{14.75,21.75,25.25},{15.25,22.5,26.25},{15.75,23.5,27.25},{16.5,24,28},{16.75,24.75,28.75},{17.25,25.25,29.5},{17.5,25.75,30.25},{17.75,26,31},{17.75,26.5,31.75}};
    void menu()
    {
        //only if the option entered is from 1-6 does the progrogram continue..
        //if the entered option is less than 1 or greater than 6 the screen refreshes and starts over
        while(opt<1 || opt>6)
        {
            System.out.println("\f"); //clears screen before user chooses the next option
            Scanner sc = new Scanner(System.in);
            System.out.println("\t\t\t~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~");
            System.out.println("\t\t\t                        Are you Healthy???");
            System.out.println("\t\t\t                        Find out!!! :D :D");
            System.out.println("\t\t\t        ___________________________________________________");
            System.out.println("\t\t\t        |                                                 |");
            System.out.println("\t\t\t        |  1 - Calculate your BMI (Body Mass Index)       |");
            System.out.println("\t\t\t        |  2 - Calculate your BMR (Basal Metabolic Rate)  |");
            System.out.println("\t\t\t        |  3 - Calculate your daily calorie requirement   |");
            System.out.println("\t\t\t        |  4 - Are you at risk to Heart disease/diabetes? |");
            System.out.println("\t\t\t        |  5 - How much should you excercise?             |");
            System.out.println("\t\t\t        |  6 - Help Menu                                  |");
            System.out.println("\t\t\t        |_________________________________________________|");
            System.out.println("\n\t\t\t~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~");
            System.out.print("\t\t\t                    Please enter your choice [1-6]: ");

            if(sc.hasNextInt())
            {
                opt = sc.nextInt();
                if(opt==1 || opt==2 || opt==3 || opt==4 || opt==5)
                {
                    runCount++; //count of the number of times this method has been run to prevent asking questions again
                    store();
                    break;
                }
                else if(opt==6)//choice helpMenu is chosen
                    helpMenu();
                else
                {
                    System.out.print("\n\t\t\tError: Bad Input. [Hit any key to continue]: ");
                    char badChoice = sc.next().charAt(0);
                    continue; //if option inputted is not 1-6 the program will refresh until the right option is inputted
                }
            }
            else
            {
                Scanner sc1 = new Scanner(System.in);
                System.out.print("\n\t\t\tError: Bad Input. [Hit any key to continue]: ");
                if(sc1.hasNextLine())
                {
                    String badChoice = sc1.nextLine();
                    continue; //if option inputted is not 1-6 the program will refresh until the right option is inputted
                } 

            }
        }
    }

    void store()
    {
        Scanner sc = new Scanner(System.in);
        if(runCount==1)//to prevent asking questions again and again
        {
            System.out.println("\n\t\t\tM: Metric system\t I: Imperial system");
            System.out.print("\t\t\tChoose your unit system [M/I]: ");
            cho = sc.nextLine().charAt(0);
            if(cho=='m'||cho=='M')
            {
                met=true; //to find out if the user inputs values in metric or imperial system
                System.out.println("\n\t\t\tPlease enter values in CENTIMETRES and KILOGRAMMES");
            }
            else
            {
                met=false;
                System.out.println("\n\t\t\tPlease enter values in INCHES and POUNDS");
            }
            System.out.println("\t\t\tEnter the following information");
            System.out.print("\t\t\tName: ");
            name = sc.nextLine();

            System.out.print("\t\t\tAge: ");
            if(sc.hasNextInt())//checks if value inputted for age is an integer
                age = sc.nextInt();
            else
            {
                System.out.println("\t\t\tPlease enter an INTEGER value ONLY");
                this.age = sc.nextInt();
            }

            System.out.print("\t\t\tGender(F/M): ");
            sex = sc.next().charAt(0);
            sex = Character.toUpperCase(sex);
            while(sex!='F' && sex!='M')//to make sure user enters only F or M
            {
                System.out.println("\t\t\tPlease enter 'F'(female) or 'M'(male) only");
                this.sex = sc.next().charAt(0);
                this.sex = Character.toUpperCase(sex);
            }

            System.out.print("\t\t\tWeight: ");
            weight = sc.nextFloat();

            System.out.print("\t\t\tHeight: ");
            height = sc.nextFloat();

            System.out.print("\t\t\tWaist size: ");
            waist = sc.nextFloat();

            System.out.print("\t\t\tHip size: ");
            hip = sc.nextFloat();

            if(met==false)//if values entered are in imperial units, the values are converted to metric
                convert();
        }
        switch(opt) //runs a method based on the chosen option
        {
            case 1:
            calcBmi();
            case 2:
            calcBmr();
            case 3:
            calcCalorie();
            case 4:
            calcRisk();
            case 5:
            calcExcercise();
        }
    }

    void helpMenu()//to give the user information about what they are calculating
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("\t\t\t~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~");
        System.out.println("\t\t\tNeed some help???");
        System.out.println("\t\t\tAll these biological terms can be mindboggling :D :D");
        System.out.println("\t\t\t~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~");
        System.out.println("\t\t\tWhat does BMI mean?");
        System.out.println("\t\t\tBMI stands for \"body mass index.\" \n\t\t\tIt is calculated by dividing your weight in kilograms \n\t\t\tby the square of your height in metres.."); 
        System.out.println("\n\t\t\tBMI is an assessment tool for determining whether \n\t\t\tyour weight falls within one of four categories:");
        System.out.println("\t\t\tUnderweight\t\tBMI is under 18.5\n\t\t\tNormal Weight\t\tBMI is 18.5 to 24.9\n\t\t\tOverweight\t\tBMI is 25 to 29.5\n\t\t\tObese\t\t\tBMI is above 30");
        System.out.println("\n\t\t\t~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~");
        System.out.println("\t\t\tWhat does BMR mean?");
        System.out.println("\t\t\t(BMR) refers to the minimum amount of energy (in calories) \n\t\t\tthat your body requires to complete its normal functions, such as: \n\t\t\tbreathing, breaking down food, and keeping your heart and brain working."); 
        System.out.println("\t\t\tAge, gender, weight, and physical activity directly affect basal metabolic rate.");
        System.out.println("\n\t\t\t~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~");
        System.out.println("\t\t\tWhat is the waist to hip ratio?");
        System.out.println("\t\t\tYour waist to hip ratio determines your risks for:\n \t\t\theart disease, hypertension, diabetes and obesity");
        System.out.println("\t\t\tThe result of this calculator helps you know your health risks-- \n\t\t\tlow, moderate or high");
        System.out.println("\n\t\t\t~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~");
        System.out.println("\t\t\tWhat is waist to height ratio?");
        System.out.println("\t\t\tThe Waist Height Ratio is an indicator of the body shape. \n\t\t\tIt helps assess your risk for:\n \t\t\tobesity, Heart diseases, Diabetes, stroke and Hypertension");
        System.out.println("\t\t\tIt is a stronger predictor of cardio-metabolic risks than BMI.");
        System.out.println("\t\t\tGreater waist circumference is linked to high morbidity and mortality risks.");
        System.out.println("\t\t\tMen and women should keep their waist circumference to \n\t\t\tno more than half their height");
        System.out.println("\n\t\t\t~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~");
        System.out.println("\n\n\t\t\tAre you ready to find out how healthy you are? \n\t\t\tEnter y/n");

        cho = sc.next().charAt(0);
        if(cho=='y' || cho=='Y')
        {
            System.out.println("\f");
            Health_Monitor h = new Health_Monitor();
            h.menu();
        }
        else
            System.exit(0);
    }

    void calcBmi()
    {
        bmi=0.0;
        //to calculate bmi
        bmi = ((double)weight/Math.pow(height,2)*7030);
        System.out.println("\n\t\t\t____________________________________________________________");              
        System.out.println("\n\t\t\tDIAGNOSIS for " + name);
        System.out.println("\t\t\t____________________________________________________________");      
        System.out.println("\t\t\tYour BMI(Body Mass Index) is: " + bmi);
        //check and print if person obese/overweight/normal/underweight
        if(age>20)//checks against ideal bmi values for adults
        {
            if(bmi<18)
                System.out.println("\t\t\t" + name + " you are UNDERWEIGHT");
            else if(bmi<25)
                System.out.println("\t\t\t" + name + " you have a NORMAL, healthy body.");
            else if(bmi<30)
                System.out.println("\t\t\t" + name + " you are OVERWEIGHT");
            else if(bmi>=30)
                System.out.println("\t\t\t" + name + " you are OBESE");
            System.out.println("\t\t\t____________________________________________________________"); 
            System.out.println("\n\t\t\t____________________________________________________________");
            System.out.println("\n\t\t\tRECOMMENDATION for " + name);
            System.out.println("\t\t\t____________________________________________________________");
            System.out.println("\n\t\t\tAn ideal BMI for your age should be in the range '18-25'");
            if(bmi<18)
                System.out.println("\t\t\tYour BMI is " + (18-bmi) + " under the normal BMI range");
            else
                System.out.println("\t\t\tYour BMI is " + (bmi-25) + " above the normal BMI range");
            System.out.println("\t\t\t____________________________________________________________");
        }
        else if(age<=20)//checks against ideal bmi values for children
        {
            if(sex=='M')//if boy
            {
                //in array boy - age ranges from 2 to 20... so the row to check will be age-1 (array index starts from 0)
                int row=age-1;
                if(bmi<=boy[row][0])
                    System.out.println("\t\t\t" + name + " you are UNDERWEIGHT!");
                else if(bmi<=boy[row][1])
                    System.out.println("\t\t\t" + name + " you have a NORMAL, healthy body.");
                else if(bmi<=boy[row][2])
                    System.out.println("\t\t\t" + name + " you are OVERWEIGHT");
                else if(bmi>boy[row][2])
                    System.out.println("\t\t\t" + name + " you are OBESE");
                System.out.println("\t\t\t____________________________________________________________"); 
                System.out.println("\n\t\t\t_________________________________________________________________________"); 
                System.out.println("\n\t\t\tRECOMMENDATION for " + name);
                System.out.println("\t\t\t_________________________________________________________________________"); 
                System.out.println("\t\t\tFor your age and gender, your BMI should be in the range " + (boy[row][0]+0.01) + "-" + boy[row][1]);
                if(bmi<=boy[row][0])
                    System.out.println("\t\t\tYour BMI is " + (boy[row][0]+1.0 - bmi) + " below the normal BMI range");
                else if(bmi>boy[row][1])
                    System.out.println("\t\t\tYour BMI is " + (bmi - boy[row][1]) + " above the normal BMI range");
                System.out.println("\t\t\t_________________________________________________________________________");
            }
            else if(sex=='F')
            {
                //in array girl - age ranges from 2 to 20... so the row to check will be age-1 (array index starts from 0)
                int row=age-1;
                if(bmi<=girl[row][0])
                    System.out.println("\t\t\t" + name + " you are UNDERWEIGHT!");
                else if(bmi<=girl[row][1])
                    System.out.println("\t\t\t" + name + " good, you have a NORMAL, healthy body.");
                else if(bmi<=girl[row][2])
                    System.out.println("\t\t\t" + name + " you are OVERWEIGHT");
                else if(bmi>girl[row][2])
                    System.out.println("\t\t\t" + name + " you are OBESE");
                System.out.println("\t\t\t_________________________________________________________________________"); 
                System.out.println("\n\t\t\t_________________________________________________________________________"); 
                System.out.println("\n\t\t\tRECOMMENDATION for " + name);
                System.out.println("\t\t\t_________________________________________________________________________");              
                System.out.println("\t\t\tFor your age and gender, your BMI should be in the range " + (girl[row][0]+1.0) + "-" + girl[row][1]);
                if(bmi<=girl[row][0])
                    System.out.println("\t\t\tYour BMI is " + (girl[row][0]+1.0 - bmi) + " below the normal BMI range");
                else if(bmi>girl[row][1])
                    System.out.println("\t\t\tYour BMI is " + (bmi - girl[row][1]) + " above the normal BMI range");
                System.out.println("\t\t\t_________________________________________________________________________");                    
            }
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("\n\n\t\t\tDo you wish to continue? enter y/n");
        cho = sc.next().charAt(0);
        if(cho=='y' || cho=='Y')
        {
            System.out.println("\f");
            Health_Monitor h = new Health_Monitor();
            h.menu();
        }
        else
            System.exit(0);
    }

    void calcBmr()
    {
        bmr=0.0;
        System.out.println("\n\n\t\t\t~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~");
        System.out.println("\t\t\tYour BMR (Basal Metabolic Rate) is an estimate of how many calories you'd burn if \n\t\t\tyou were to do nothing but rest for 24 hours. It represents the minimum amount \n\t\t\tof energy needed to keep your body functioning, including breathing and keeping \n\t\t\tyour heart beating.");
        System.out.println("\t\t\tYour BMR does not include the calories you burn from normal daily activities or exercise.");
        System.out.println("\t\t\t~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~");

        System.out.println("\n\t\t\t____________________________________________________________"); 
        System.out.println("\n\t\t\tDIAGNOSIS for " + name);
        System.out.println("\t\t\t____________________________________________________________"); 
        
        if(sex=='F')
        {
            //calculates bmr for women
            bmr = 655 + (9.6*weight) + (1.8*height) - (4.7*age);
            System.out.println("\t\t\t Your BMR(Basal Metabolic Rate) is: " + bmr);
            System.out.println("\t\t\t____________________________________________________________");

        }
        else if(sex=='M')
        {
            //calculates bmr for men
            bmr = 66 + (13.7*weight) + (5*height) - (6.8*age);
            System.out.println("\t\t\t Your BMR(Basal Metabolic Rate) is: " + bmr);
            System.out.println("\t\t\t____________________________________________________________");
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\n\t\t\tDo you wish to continue? enter y/n");
        cho = sc.next().charAt(0);
        if(cho=='y' || cho=='Y')
        {
            System.out.println("\f");
            Health_Monitor h = new Health_Monitor();
            h.menu();
        }
        else
            System.exit(0);
    }

    void calcCalorie()
    {
        if(sex=='F')
        {
            //calculates bmr for women in case they have chosen option 3 before choosing 2 (and calculating bmi)
            bmr = 655 + (9.6*weight) + (1.8*height) - (4.7*age);

            System.out.println("\n\t\t\t~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~");
            System.out.println("\t\t\tYour BMR(Basal Metabolic Rate) is: " + bmr);
            System.out.println("\t\t\t~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~");
        }
        else if(sex=='M')
        {
            //calculates bmr for men in case they have chosen option 3 before choosing 2(and calculating bmi)
            bmr = 66 + (13.7*weight) + (5*height) - (6.8*age);

            System.out.println("\n\t\t\t~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~");
            System.out.println("\t\t\tYour BMR(Basal Metabolic Rate) is: " + bmr);
            System.out.println("\t\t\t~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~");
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\t\t\tHow much do you excercise?");
        System.out.println("\t\t\t(a) Don't excercise or excercise a little");
        System.out.println("\t\t\t(b) Light excercise or Play sports 1-3 days a week");
        System.out.println("\t\t\t(c) Moderate excercise or Play sports 3-5 days a week");
        System.out.println("\t\t\t(d) Hard excercise and/or Play strenuous sports 6-7 days a week");
        System.out.println("\t\t\t(e) Physically challenging jobs/excercise or 2 a day workouts");
        System.out.println("\t\t\tPlease enter a, b, c, d or e ...");
        char choice = sc.next().charAt(0);
        choice = Character.toLowerCase(choice);
        if(choice=='a')
            System.out.println("\t\t\tYour daily calorie requirement is: " + (bmr*1.2) + " cal");
        else if(choice=='b')
            System.out.println("\t\t\tYour daily calorie requirement is: " + (bmr*1.375) + " cal");
        else if(choice=='c')
            System.out.println("\t\t\tYour daily calorie requirement is: " + (bmr*1.55) + " cal");
        else if(choice=='d')
            System.out.println("\t\t\tYour daily calorie requirement is: " + (bmr*1.725) + " cal");
        else if(choice=='e')
            System.out.println("\t\t\tYour daily calorie requirement is: " + (bmr*1.9) + " cal");
        else
        {
            //if input choice is not a,b,c,d, or e the page refreshes and asks only for those options
            System.out.println("\f");
            System.out.println("\t\t\tPlease enter a, b, c, d or e ONLY...");
            calcCalorie();
        }
        System.out.println("\n\n\t\t\tDo you wish to continue? enter y/n");
        cho = sc.next().charAt(0);
        if(cho=='y' || cho=='Y')
        {
            System.out.println("\f");
            Health_Monitor h = new Health_Monitor();
            h.menu();
        }
        else
            System.exit(0);
    }

    void calcRisk()
    {
        double waistHipRatio=0.0;

        System.out.println("\n\t\t\t~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~");
        System.out.println("\t\t\tYour waist to hip ratio determines your risks for:\n \t\t\theart disease\n \t\t\thypertension\n \t\t\tdiabetes\n \t\t\tobesity");
        System.out.println("\t\t\tThe result of this calculator helps you know your health risks-- \n\t\t\t--> low\n\t\t\t--> moderate\n\t\t\t--> high");
        System.out.println("\t\t\t~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~");
        //calculates waist to hip ratio
        waistHipRatio= (double)waist/hip;
        System.out.println("\t\t\t____________________________________________________________");
        System.out.println("\n\t\t\tDIAGNOSIS for " + name);
        System.out.println("\t\t\t____________________________________________________________");
        System.out.println("\t\t\tYour Waist to Hip ratio is: " + waistHipRatio);
        

        //to check if the health risk is low, moderate or high based on gender
        if(sex=='M')
        {
            if(waistHipRatio<0.96)
                System.out.println("\t\t\tYour health risk is LOW");
            else if(waistHipRatio<1.0)
                System.out.println("\t\t\tYour health risk is MODERATE");
            else if(waistHipRatio>=1.0)
                System.out.println("\t\t\tYour health risk is HIGH");
        }
        else if(sex=='F')
        {
            if(waistHipRatio<0.81)
                System.out.println("\t\t\tYour health risk is LOW");
            else if(waistHipRatio<0.85)
                System.out.println("\t\t\tYour health risk is MODERATE");
            else if(waistHipRatio>=0.85)
                System.out.println("\t\t\tYour health risk is HIGH");
        }
        System.out.println("\t\t\t____________________________________________________________");
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\n\t\t\tDo you wish to continue? enter y/n");
        cho = sc.next().charAt(0);
        if(cho=='y' || cho=='Y')
        {
            System.out.println("\f");
            Health_Monitor h = new Health_Monitor();
            h.menu();
        }
        else
            System.exit(0);
    }

    void calcExcercise()
    {
        double waistHeightRatio=0.0;
        System.out.println("\n\t\t\t~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~");
        System.out.println("\t\t\tThe Waist Height Ratio is an indicator of the body shape. \n\t\t\tIt helps assess your risk for:\n \t\t\t-> obesity\n \t\t\t-> Heart diseases\n \t\t\t-> Diabetes\n \t\t\t-> stroke\n \t\t\t-> Hypertension");
        System.out.println("\t\t\tIt is a stronger predictor of cardio-metabolic risks than BMI.");
        System.out.println("\t\t\tGreater waist circumference is linked to high morbidity and mortality risks.");
        System.out.println("\t\t\tMen and women should keep their waist circumference to \n\t\t\tno more than half their height");
        System.out.println("\t\t\t~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~");
        //calculates Waist to Height ratio   
        waistHeightRatio= (double)waist/height;
        System.out.println("\n\n\t\t\tYour Waist to Height ratio is: " + waistHeightRatio);

        //to tell the user how much they must excercise and how healthy they are
        if(waistHeightRatio<=0.39)
            System.out.println("\t\t\tTake Care");
        else if(waistHeightRatio<=0.5)
            System.out.println("\t\t\tGood. Maintain this ratio");
        else if(waistHeightRatio<=0.59)
            System.out.println("\t\t\tConsider excercise regime");
        else if(waistHeightRatio<=1.0)
            System.out.println("\t\t\tWork seriously on your excercise regime and diet");
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\n\t\t\tDo you wish to continue? enter y/n");
        cho = sc.next().charAt(0);
        if(cho=='y' || cho=='Y')
        {
            System.out.println("\f");
            Health_Monitor h = new Health_Monitor();
            h.menu();
        }
        else
            System.exit(0);
    }

    //to convert imperial values to metric
    void convert()
    {
        this.height = this.height*2.54;
        this.weight = this.weight/2.2;
        this.waist = this.waist*2.54;
        this.hip = this.hip*2.54;
    }

}
