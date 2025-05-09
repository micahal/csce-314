--Use randomRIO (1,6) to generate a random dice roll.
--Use getLine to take user input, then convert it to an integer using read.
--Use recursion or mapM to play five rounds in a row.
--Use an accumulator to store and update the highest score across games.
import System.Random
--we should create a function to gen random num
roll :: IO Int
roll = randomRIO (1,6)
-- function that takes in our users input and validates it
userNum :: IO Int
userNum = do
    putStr "Guess the dice roll (1-6): "
    input <- getLine
    case reads input of
        [(n, "")] | n >= 1 && n <= 6 -> return n  -- Valid number
        _ -> do
            putStrLn "Invalid input. Please enter a number between 1 and 6."
            userNum 


--function to check what points the user gets, and keeps an accumulator
score :: Int -> Int -> Int
score userN ranN
    | userN == ranN = 5
    | abs(userN - ranN) == 1 = 1
    | otherwise = 0

-- function to run a single round
doRound :: Int -> IO Int
doRound acc = do
    guess <- userNum
    actual <- roll
    let add = score guess actual
    let update = add + acc
    putStrLn $ "The dice rolled: " ++ show actual
    putStrLn $ "You earned " ++ show add ++ " points!" ++ "\n"
    return update

-- function to recursively do 5 rounds
rounds :: Int -> Int -> IO Int
-- our base case is when rounds is 0
rounds 0 acc = return acc
rounds n acc = do
    -- we need something to display the round
    putStrLn ("--- Round " ++ show (6 - n) ++ " ---")
    new <- doRound acc
    rounds (n - 1) new

--function to play
playGame :: Int -> IO Int
playGame acc = do
    putStrLn "\nStarting a new game...\n"
    final <- rounds 5 0 
    let newFinal = max acc final
    putStrLn $ "Game over! Your total score is: " ++ show final
    putStrLn $ "Highest score so far: " ++ show newFinal
    playAgain newFinal

--function to ask to play again
playAgain :: Int -> IO Int
playAgain acc = do
    putStr "\nDo you want to play again? (y/n): "
    input <- getLine
    case input of
        "y" -> playGame acc
        "n" -> do
            putStrLn "\nThanks for playing! Goodbye!"
            return acc
        _ -> do
            playAgain acc


main :: IO ()
main = do
    putStrLn "Welcome to the Dice Guessing Game!"
    _ <- playGame 0
    return ()

    




