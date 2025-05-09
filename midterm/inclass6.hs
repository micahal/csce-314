{- Friday 2/28 inclass assignment.
CSCE 314
Spring 2025
-}

import Control.Monad (when)
import Text.Read (readMaybe)

------------------------------------------------------------
-- Section 1: Maybe Monad
------------------------------------------------------------
-- This function takes a string input, attempts to convert it
-- to an integer, and then adds 10. Students should implement
-- the computation using the Maybe monad with >>= bindings.
--
-- TODO: Complete or modify the function using >>=
maybeCompute :: String -> Maybe Int -- TODO fill in the monad
maybeCompute str =
    -- Using >>= binding:
    readMaybe str >>= (\n -> Just (n + 10)) -- TODO add 10 to the value

-- Example: 
-- input: 8, output: 18
-- input: 10, output: 20

------------------------------------------------------------
-- Section 2: Either Monad
------------------------------------------------------------
-- This function takes two string inputs, converts them to integers,
-- and divides the first by the second. Proper error messages must be
-- returned if the conversion fails or if there is a division by zero.
--
-- TODO: Complete the function using the Either monad with proper error handling
eitherCompute :: String -> String -> Either String Int -- TODO fill in the monad
eitherCompute s1 s2 = do
    x <- case readMaybe s1 of
            Just n -> Right n -- TODO return number
            Nothing -> Left "N/A" -- TODO return error text
    y <- case readMaybe s2 of
            Just n -> Right n -- TODO return number
            Nothing -> Left "N/A" -- TODO return error text
    if y == 0
      then Left "Division by zero error"
      else Right (x `div` y)

------------------------------------------------------------
-- Section 3: IO Monad and Sequencing
------------------------------------------------------------
-- This interactive section uses IO monad sequencing and >>= binding.
-- Students will see how to chain IO actions and use the bind operator.
--
-- TODO: Modify or extend this function to demonstrate more IO monad operations
ioProgram :: IO ()
ioProgram = do
    putStrLn "Enter a number for the Maybe computation (it will add 10):"
    input1 <- getLine -- TODO read in and store in input 1
    let maybeResult = maybeCompute input1  
    putStrLn $ "Maybe result: " ++ show maybeResult -- TODO Display the maybeResult

    putStrLn "\nEnter two numbers for the Either computation (first divided by second):"
    putStrLn "Enter first number:"
    input2 <- getLine
    putStrLn "Enter second number:"
    input3 <- getLine
    let eitherResult = eitherCompute input2 input3
    putStrLn $ "Either result: " ++ show eitherResult

    -- Demonstrate explicit usage of >>= in IO actions:
    putStrLn "\nType anything to see a demonstration of >>= binding in IO:"
    getLine >>= (\x -> putStrLn $ "You entered: " ++ x) -- TODO complete the binding for IO

------------------------------------------------------------
-- Main Function
------------------------------------------------------------
-- TODO: Modify main if needed and ensure proper sequencing of operations.
main :: IO ()
main = do
    putStrLn "Starting Monads Assignment Program"
    ioProgram
    putStrLn "Program Finished."