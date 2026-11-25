data Expr = Numero Int
          | Soma Expr Expr
          | Mult Expr Expr
          | Div Expr Expr
          deriving (Show, Eq)

avaliar :: Expr -> Maybe Int
avaliar (Numero n) = Just n
avaliar (Soma a b) =
    case (avaliar a, avaliar b) of
        (Just x, Just y) -> Just (x + y)
        _                -> Nothing
avaliar (Mult a b) =
    case (avaliar a, avaliar b) of
        (Just x, Just y) -> Just (x * y)
        _                -> Nothing
avaliar (Div a b) =
    case (avaliar a, avaliar b) of
        (Just _, Just 0) -> Nothing
        (Just x, Just y) -> Just (quot x y)
        _                -> Nothing

mostrar :: Expr -> String
mostrar (Numero n) = show n
mostrar (Soma a b) = "(" ++ mostrar a ++ " + " ++ mostrar b ++ ")"
mostrar (Mult a b) = "(" ++ mostrar a ++ " * " ++ mostrar b ++ ")"
mostrar (Div a b)  = "(" ++ mostrar a ++ " / " ++ mostrar b ++ ")"

simplificar :: Expr -> Expr
simplificar (Numero n) = Numero n
simplificar (Soma a b) =
    case (simplificar a, simplificar b) of
        (x, Numero 0) -> x
        (Numero 0, y) -> y
        (x, y)        -> Soma x y
simplificar (Mult a b) =
    case (simplificar a, simplificar b) of
        (x, Numero 1) -> x
        (Numero 1, y) -> y
        (x, y)        -> Mult x y
simplificar (Div a b) = Div (simplificar a) (simplificar b)

contar :: Expr -> Int
contar (Numero _) = 0
contar (Soma a b) = 1 + contar a + contar b
contar (Mult a b) = 1 + contar a + contar b
contar (Div a b)  = 1 + contar a + contar b

ex1 :: Expr
ex1 = Soma (Numero 3) (Numero 4)

ex2 :: Expr
ex2 = Div (Numero 8) (Numero 0)

ex3 :: Expr
ex3 = Mult (Numero 2) (Soma (Numero 3) (Numero 4))

ex4 :: Expr
ex4 = Mult (Soma (Numero 3) (Numero 4)) (Numero 2)

ex5 :: Expr
ex5 = Soma (Numero 1) (Div (Numero 5) (Numero 0))

ex6 :: Expr
ex6 = Div (Numero (-7)) (Numero 2)

ex7 :: Expr
ex7 = Soma (Mult (Numero 5) (Numero 1)) (Numero 0)

ex8 :: Expr
ex8 = Mult (Soma (Numero 1) (Numero 0)) (Numero 7)

ex9 :: Expr
ex9 = Mult (Div (Numero 1) (Numero 0)) (Numero 0)

ex10 :: Expr
ex10 = Div (Numero 10) (Soma (Numero 2) (Numero 3))

main :: IO ()
main = do
    putStrLn "Projeto 1 - Calculadora de Expressoes"
    putStrLn "Integrantes: Emanuel Filipy, Matheus Caetano, Wesley Julio"
    putStrLn ""

    putStrLn "===== avaliar ====="
    putStrLn ("normal:   " ++ mostrar ex1 ++ " = " ++ show (avaliar ex1))
    putStrLn ("normal:   " ++ mostrar ex4 ++ " = " ++ show (avaliar ex4))
    putStrLn ("especial: " ++ mostrar ex2 ++ " = " ++ show (avaliar ex2))
    putStrLn ("especial: " ++ mostrar ex5 ++ " = " ++ show (avaliar ex5))
    putStrLn ("especial: " ++ mostrar ex6 ++ " = " ++ show (avaliar ex6))
    putStrLn ""

    putStrLn "===== mostrar ====="
    putStrLn ("normal:   " ++ mostrar ex3)
    putStrLn ("especial: " ++ mostrar (Numero 5))
    putStrLn ""

    putStrLn "===== simplificar ====="
    putStrLn ("normal:   " ++ mostrar ex7 ++ "  vira  " ++ mostrar (simplificar ex7))
    putStrLn ("normal:   " ++ mostrar ex8 ++ "  vira  " ++ mostrar (simplificar ex8))
    putStrLn ("especial: " ++ mostrar ex9 ++ "  vira  " ++ mostrar (simplificar ex9))
    putStrLn ""

    putStrLn "===== a expressao original nao muda ====="
    putStrLn ("antes:  " ++ show ex7)
    putStrLn ("depois: " ++ show (simplificar ex7))
    putStrLn ("ex7 continua: " ++ show ex7)
    putStrLn ""

    putStrLn "===== contar ====="
    putStrLn ("normal:   " ++ mostrar ex3 ++ " tem " ++ show (contar ex3) ++ " operacoes")
    putStrLn ("normal:   " ++ mostrar ex10 ++ " tem " ++ show (contar ex10) ++ " operacoes")
    putStrLn ("especial: " ++ mostrar (Numero 5) ++ " tem " ++ show (contar (Numero 5)) ++ " operacoes")
    putStrLn ""

    putStrLn "===== simplificar nao muda o resultado ====="
    putStrLn (mostrar ex7 ++ ": " ++ show (avaliar ex7) ++ " e " ++ show (avaliar (simplificar ex7)))
    putStrLn (mostrar ex8 ++ ": " ++ show (avaliar ex8) ++ " e " ++ show (avaliar (simplificar ex8)))
    putStrLn (mostrar ex9 ++ ": " ++ show (avaliar ex9) ++ " e " ++ show (avaliar (simplificar ex9)))
