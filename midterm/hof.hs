-- Define a function to double a number
double :: Int -> Int
double x = x + x

-- Define a function to triple a number

triple :: Int -> Int
triple x = x * 3


-- Create a higher-order function that applies a function to a number

applyFunction :: (Int->Int) -> Int -> Int
applyFunction f n = f n

-- Define a function that returns another function

multiplyBy :: Int -> (Int -> Int)
multiplyBy n f = n * f

-- Create a composition function that combines two functions

comp :: Int -> Int
comp = double.triple 

-- Define a curried function that takes a title and a name and returns a greeting

curryt :: String -> String -> String
curryt title name = "Hello " ++ title ++ " " ++ name

main :: IO ()
main = do

    -- Use the higher-order function to apply the doubling function to a number
    print(applyFunction double 7)
    
    -- Use the higher-order function to apply the tripling function to a number
    print(applyFunction triple 10)
    
    -- Create an example of a function that returns another function and apply it
    let multiplyBy7 = multiplyBy 7
    print(multiplyBy7 10)
    
    -- Use the composition function on a number
    print(comp 10)
    
    -- Define a lambda function that squares a number and apply it
    let squares x = x * x
    print(squares 10)
    
    -- Use a lambda function with `map` to square a list of numbers and print the result
    let numbers = [1, 2, 3, 4, 5]
    print(map (\x -> squares x) numbers)
    
    -- Use `filter` with a lambda function to filter even numbers from a list and print the result
    let test = filter even
    print(test numbers)
    
    -- Apply a curried function to create personalized greetings and print the results
    let currytMr = curryt "Mr"
    let currytMs = curryt "Ms"
    print(currytMr "don")
