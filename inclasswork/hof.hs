double :: Int -> Int
double x = x + x

triple :: Int -> Int
triple x = x * 3

-- A higher order function that applies a function to a num
applyFunction :: (Int -> Int) -> Int -> Int
applyFunction f = f

-- define a function that returns another function

multiplyBy :: Int -> (Int -> Int)
multiplyBy n x = n * x

--composition function
compisitionFunction :: Int -> Int
compisitionFunction = triple.double

-- curried function
greet :: String -> String -> String
greet title name = "Howdy, " ++ title ++ " " ++ name ++ "!"


main :: IO ()
main = do
    

--higher order function applies a function to an int

    print (applyFunction double 77)
    print(applyFunction triple 33)
    
-- an example of a function that returns another function
    let multiplyBy6 = multiplyBy 6
    print(multiplyBy6 4)
--using a compisition function
    print(compisitionFunction 3)

--lambda expression
    let square x = x * x
    print(square 5)

--lambda function applied to a list
    let numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    let squares = map (\x -> x * x) numbers
    print squares

    let filterEven = filter even
    print (filterEven [1, 2, 3, 4])

-- APPLICATION OF A CURRIED FUNCTION
    let greetMr = greet "Mr."
    let greetMs = greet "Ms."
    let greetFriends = greet "friend"
    print (greetMr "Will")
    print (greetMs "Chavez")
    print (greetFriends "Armaan")
