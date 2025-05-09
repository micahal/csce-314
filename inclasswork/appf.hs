applyAll :: [a -> b] -> a -> [b]
applyAll fs x = map (`applyFunction` x) fs

applyFunction :: (a->b) -> a -> b
applyFunction f = f

--example functions
double :: Int -> Int
double n = n * 2

square :: Int -> Int
square n = n * n

main :: IO ()
main = do
    let functions = [double, square]
    print $ applyAll functions 5       --output [10, 25]