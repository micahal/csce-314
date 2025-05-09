{-
Micah Alummoottil
3/4/25
fill in the functions below, there is no need to change main.
-}

import System.IO

-- Define the type for family data  (done for you)
type Family = (String, String, Int)

-- Read the file and parse it into a list of Family tuples  (done for you)
parseFamilyData :: String -> [Family]
parseFamilyData content =
    map parseLine (lines content)
    where
        parseLine line = let parts = split ',' line
                         in (trim (parts !! 0), trim (parts !! 1), read (trim (parts !! 2)) :: Int)

-- Utility function to split a string by a delimiter (Done for you -used to read in file)
split :: Char -> String -> [String]
split _ [] = []
split delim str =
    let (first, rest) = break (== delim) str
    in first : case rest of
        [] -> []
        (_:xs) -> split delim xs

-- Trim whitespace from a string (Done for you -used to read in file)
trim :: String -> String
trim = unwords . words

-- 1. Count the number of families
countFamilies :: [Family] -> Int
countFamilies l = length l
-- Write this function

-- 2. Compute the total number of family members
totalMembers :: [Family] -> Int
totalMembers [] = 0
totalMembers ((_, _, n):rest) = n + totalMembers rest
--write this function

third :: (a, b, c) -> c
third (_, _, c) = c

-- 3. Find the family with the most members
largestFamily :: [Family] -> (String, Int)
largestFamily l = 
    let largest = foldl1 (\f1 f2 -> if third f1 > third f2 then f1 else f2) l
    in case largest of
        (name, _, num) -> (name, num)


--write this function

-- 4. Filter families with more than a given number of members
filterLargeFamilies :: Int -> [Family] -> [Family]
filterLargeFamilies n l = filter (\(_, _, x) -> x > n) l
--write this function

-- 5. Extract and format addresses
formatAddresses :: [Family] -> [String]
formatAddresses l = map (\(_, add, _) -> add) l

--write this function

-- Main function to read the file and execute the functions
main :: IO ()
main = do
    handle <- openFile "families.txt" ReadMode
    content <- hGetContents handle
    let familyData = parseFamilyData content

    putStrLn $ "Number of families: " ++ show (countFamilies familyData)
    putStrLn $ "Total number of people: " ++ show (totalMembers familyData)
    putStrLn $ "Largest family: " ++ show (largestFamily familyData)
    putStrLn $ "Families with more than 3 members: " ++ show (filterLargeFamilies 3 familyData)
    putStrLn $ "Addresses: " ++ show (formatAddresses familyData)

    hClose handle