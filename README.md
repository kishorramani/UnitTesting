Unit Testing 

Video 1 => https://youtu.be/9yre-M1XwVw?si=UdPQAj9NjgDGD2uD [Android Unit Testing Tutorial | Introduction]
=> What & Why
- Testing smallest piece of code in isolation
- Helps in catching bugs
- Help in writing modular code - feather can be added easily

=> Testing in Android
- We have kotlin/Java code (Pure logic) with Android Framework
- Finding unit in Android is complex
- Having good architecture helps in segregating Android Code from pure Kotlin/Java code

=> Two Types of Test
1: Pure Logic/Java logic can be tested with the JVM (Desktop only - JUnit)
2: For Android tests, we need device (Instrumentation Test)
Android test can be further divided into 2 type
2A: UI Tests (Espresso)
2B: Non UI Test

Unit Test
1: JVM Test (Local Unit Test)
2: On Device Test (Instrumentation Test)
    -> UI Tests (Interaction with UI)
    -> Non UI Tests (Context, Assets Manager, etc)

=> Major Topic
JUnit -> JVM Tests (testImplementation)
Mockito -> Mock Object or Fake Objects (androidTestImplementation)
Espresso -> UI Interactions (androidTestImplementation)

=> Dependency
test folder (for JUnit)
androidTest (Instrumentation test)

testImplementation(libs.junit)
androidTestImplementation(libs.androidx.junit)
androidTestImplementation(libs.androidx.espresso.core)

junit = { group = "junit", name = "junit", version.ref = "junit" }
androidx-junit = { group = "androidx.test.ext", name = "junit", version.ref = "junitVersion" }
androidx-espresso-core = { group = "androidx.test.espresso", name = "espresso-core", version.ref = "espressoCore" }

junit = "4.13.2"
junitVersion = "1.2.1"
espressoCore = "3.6.1"

Video 2: https://youtu.be/LdZdAofm7hk?si=nnNpF16odsEL0pkd [Android JUnit Introduction Tutorial | Parameterized Tests Kotlin]
=> Unit Test Structure [refer - (HelperTest) package .utils/Helper.kt, .utils/HelperTest.kt & ParameterizedExample.kt]
1: Arrange -> Create Objects
2: Act -> Logic execute
3: Assert -> Give Input and expected Output

To Create JUnit
Go to Code -> Generate -> Test -> Select JUnit4 (Based on dependency) -> Select Method -> Select file

@Test (import -> import org.junit.Test)
This annotation shows that it's test 

Check Helper and HelperTest class

-> Make sure that package structure will be same 

-> Name of UnitTest -> isPallindrome_inputString_level_expectedTrue
function name + input + input value + expected output

=> Coverage -> It's shows that how many line are cover 
Run HelperTest with Coverage 

- Although code coverage is 100% now but it does not mean that we have covered all the scenarios 

=> @Before 
=> @After 

=> ParameterizedExample [refer - (ParameterizedExample) package com.kishorramani.unittesting.utils/ParameterizedExample.kt]
@JvmStatic
@Parameterized.Parameters(name = "{index} : {0} is pallindrome - {1}")
-> Add this for parameterized method

@RunWith(value = Parameterized::class)
-> Add this for parameterized class

Video 3: https://youtu.be/crd4IPEJtkI?si=DH4lWRF4pUTIgeiZ [Android Instrumentation Tests Tutorial | Assert Exceptions Kotlin]
=> Instrumentation Tests - we need device [refer - (QuoteManagerTest) package QuoteManager.kt, Quote.kt, assets/malformed.json, assets/quotes.json]
Non - UI (Similar to JUnit Tests) [context, assets manager, etc]
UI (Espresso) [click on view, type on views]

- Get context in TestClass
val context = ApplicationProvider.getApplicationContext<Context>()

- Set assert like this
@Test(expected = FileNotFoundException::class)
@Test(expected = JsonSyntaxException::class)
assertEquals(6, quoteManager.quotesList.size)

Video 4: https://youtu.be/Cq7YHb5tKmQ?si=DChp4NrDNEAp012A [Android Local Unit Test - Practice Problems | Kotlin]
Problem Statement 1: Validate Password 
 - should not be empty
 - Length should between 6 & 15
 - Otherwise, return valid password

Problem Statement 2: String Reversal
 - "ABC" -> "CBA"

Video 5: https://youtu.be/r9cC5yDzNZ4?si=wzFO2BVvKNCSPBo4 [Android Local Unit Test - Solution Video | Kotlin]
Solution of above problem statement [refer (UtilsTest.kt) - package .utils/Utils.kt]

Video 6: https://youtu.be/in4plqDNuhU?si=WZ4ufpu7A9ygMDaA [Android Espresso Unit Tests - UI Tests with Example]
Quote App Example [refer (Quoteapp/QuoteActivityTest.kt) - package .quoteapp.QuoteActivity.kt]

- Check all dependencies

@Rule - Rule is an instance of class whereas 
@Before - Before is function

-> To share common code among different test cases, rules are used.

@get:Rule
val activityScenarioRule = ActivityScenarioRule(QuoteActivity::class.java)

onView -> To find the view
withId -> To find the ID from the view
perform -> To perform some action on view
click() -> To click on any view
check -> To check anything
matches -> To match something
withText -> To match the text

-> Example
onView(withId(R.id.btnNext)).perform(click())
onView(withId(R.id.quoteText)).check(matches(withText("This is quote 4")))

Intents.init() -> Initializes Intents
allOf -> Creates a matcher that matches if the examined object matches ALL of the specified matchers. "example - allOf(hasAction(Intent.ACTION_SEND))"

Quote App Example [refer (.noteapp/NoteActivityTest.kt) - package .noteapp/NoteActivity.kt]
Write title and description and match with another activity's text view

typeText("Hello") -> To type anything in view
Example - onView(withId(R.id.etTitle)).perform(typeText("Hello"))

Video 7: https://youtu.be/hK4An_jL0Q4?si=K9RKMGE1m5msAU-m [Android Unit Test - Room Database | Testing Room DB]
Room database testing

