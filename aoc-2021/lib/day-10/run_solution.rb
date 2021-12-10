require_relative 'solution'

# frozen_string_literal: true

INPUT = '
[<[{<[(([<[[{[()()]}]{<<<>[]>[()[]]>{(<>[]){<>[]}}}]<{(<()<>>{()<>})(<()>)}([{()[]}](<()<>>
<[(<({[<(<<({({}[])[<>]}[<<>{}>[()()]])>>)>]})>{[([({[{{<[[]{}]{{}}><{()[]}[{}]>}}{{[<<>()>((){})
{<({([<([[<<[(<>())({}{})](<()()>{{}})>(([[]{}]<[][]>){<{}>{<>{}}})>]][[((<[<>()]<<>[]>>{{()[]}{[]{}
([{({<<{<<<{([{}[]]([]())){[(){}]}}{[<{}()>(<>[])]}][({[()<>][<><>]}{{{}[]}})<<<{}()>>>]>([[<[{}<>]
{([{({(<{[<(<(()()){{}()}>[(<><>)<<>[]>])[{{{}()}[(){}]}[<()[]>[{}[])]]>{([{{}{}}{[]}][{{}<>}{<><>}]){[[[]()
[{(([[{([(({(<{}()>({}<>))((()())(<>[]))}{<{{}[]}[{}()]>{{()()})})<{{([]{})[<>[]]}[{<>[]}{
<<([{{({{{[(<[<>()][()<>]>)]}<(<<<<>()><{}()>>><({{}<>}{()[]}){<(){}>}>)>>})<[{[<([[{}{}]{{}()}]
<[{{[<[{(<<([([]{})]<[[]{}]{<>()}>)(<<<>{}>{{}[]]>(<[]()>(()[])))><<[{[][]}{[]}](([])([]))><({{}{}}{[][]})
{{{({{{([<<<{[()]({}{})}((()[])<()<>>)>{{<{}<>><{}()>}}>(<<(())({}())>{{{}}({}())}>{<[{}<>]<{}{}>>[{<>()}
{<({<[{[<<{{[[[][]][<><>]](([]{})<()[]>)}<<((){})><[[][]](<><>)>>}{(<<{}<>><<>>><((){})([]<>)>)<(([]))(
({{{([({<((<<<()>[[]<>]>(<<><>>(()()))>))>})]){({[<[<<[[{}{}]][([]<>)]><{<{}()><()<>>}(<[][]>[{}()])]>{({{
(<{<<[[[(<{<({<>()}{{}<>})<<()()>(<>[])>>(({{}()}([])))}>([[<({}())({}())]((<>{})<<><>>)][[<(
[([({{{[<([<[(<>[])[{}[]]]<[[]{}][<>]>>])<(((<()<>>(<>{}])({<>{}}[{}<>]))<([()()]{[]<>})({<>()}[{}
<(<<([{{[<((({<><>}<<>>)[[[]<>]({}())]){(<<>()>{[]()})[<()>{[][]}]})>]<[[[{(<>{})<(){}>}[{<>{}}]]]][
{[[[{[(((<{[{([]{})[[][]]}(<{}()><<>[]>)][<[<>{}]>]}>({([(()())<[]{}>](([]{})[<>{}])][[{[]{}}<<>{}>]({{}}[<>
{{{([[<[({<<<({}{})<[]()>>[[<>[]>{<>[]}]>[({<>[]}({}<>))[[[]()][()<>]]]>})]>]<(<<<<[<((){})<{}{}>><{()()}((
[{<<<[((<{{{[<{}{}>[{}{}]]([{}<>]{{}<>})}{{[{}{}][{}]}({[]<>}{<><>})}][([<[]<>><{}<>>][<(){}>(()
[{<(<[{(<(<((((){})<[]<>>)[{()<>}{[]<>}])><(((<>)(<>[]))(<[]<>>))([<<><>>[[]<>]]{<[]()>{()[]}})>){<({{[][
<(<<<[([[<{{(({}[])[<>{}])<(()<>)({}{})>}}>{{([(<>())<()[]>]{{[]<>}})<[[<>()]](<()[]>)>}<{[<{}<>><{}[]>](
{((<[<[<(<[({{{}<>}(()<>)}{(<>{})[[]{}]})(((()[])))]>)[{([([<><>][{}[]])[<()[]>(<>[])]]({[
[({([<([({<(<<(){}><{}<>>>{{<>[]}[()[]]})[[[{}[]](()())]]]({[{(){}}{()}]<{{}()}{{}()}>}[([[][]][()[
({[[{[[({{[[(<<><>>{()}){[()()]}]<{{<><>}({}())}{{[][]}{()()>}>]<[{<<>[]>[[]<>]}]<[<()[]><(
(<[{[(<{(<{{[{<><>}{[][]}](<(){}><[]()>)}}><{<(<<>()>([]<>))<({}<>){{}()}>>}{([(()[]}[<>[]]])<{{[]{}}
<{{(<([(<<<<[{{}<>}(()[])][<{}[]>[<>[]]]><<{[]()}(()<>)>({()<>}({}[]))>><[(([]{}))[{<><>}([][])]]{{(<>())}(
{<[(<{[[(<<[{<(){}>{<><>}}((()<>){<>()})]<[[{}<>]{{}[]}]{([])([]{})}>><{(<{}[]><()<>>)}<(({}()]{[
[{[([([{{{{([(()[])[()()]])[[(<>{})[{}[]]]]}}}{<({[<()[]><{}()]]}{[[[]()]{()()}]})<<<[{}<>](<>[])>[(()<>)([]
{(<{([<<<((({[{}<>]({}())}))[{{((){})[()()]}<{{}<>}<()[]>>}{{{[]()}}}]){((((()())(())){(<>{}){{
{<<{{{([[<{<<[{}<>][<>[]]>>(([()()](<>[])){([]{})})}>{<((<<>()><[]<>>)({[][]}[[]{}])){(<(){}><{}>)(
[[[{[{{{{[<{((<>())((){}))(([]{}){()()})}>([[[{}{}]{[]()}]{(()())<{}[]>}][{((){})<{}[]>}<<[]><
(<[[<[{[<(<[<<{}[]>[[]<>]>((()<>)[<>[]])]{[[{}<>]({}{})](<[][]><[]{}>)}>([{(<>[])<{}{}>}({(){}}[()<>])]
{{<[{({([[(([[{}<>][{}<>]][[<><>]]))<([({}()){[]()}](({}())<{}<>>))>]<{<(({}{})[[]<>])[<{}{
[[({(<[<<{<{<({}())(()[])><((){})[()<>]>}{{<()>(<>[])}}><[([[][]]<()<>>)][<(<>())[()()]>{(<>())}]>)>((<<[
[([{<<[(([((<<()[]><{}[]>>[{<>()}<()[])]){[{()[]}{<>[]}]{{<>}(()())}})<[(({}{})(()[]))(<[]
((<<{{{<<[{[<<[][]>[<><>]>(({}<>)(<><>))](<<[]<>>(<>[])>[<<>{}>((){})])}]>>(({{([<<><>>{{}
([<(([[[((<(([<>()]<()<>>)<[[]()][{}()]>){<(()<>){()()}>}>{(<(()()){{}<>}><<[]<>>[(){}]>)[<<{}{}>{[]
({[<<[{<{<<<[<{}[]>]{[[][]]{[]()}}][{<<><>>(()())}({{}[]}<{}[]>)]>>((([<{}{}>({}())]({{}()
{{[<<<[{(([<[<(){}><<>[]>]({<>[]}[<>{}])>]([<[[][]][[]{}]>({[]<>}[()<>])]))(([({{}()}{<><>})[[()()](<>[]
[([{(<[<([[(<(()[]){(){}}>)[[<[]()>([]())]({[]<>}<{}()>)]]([<{[][]}(()<>)><[{}[]][()[]]>]<[<{
(<[([<<<(<[({(())(()[])}<{<>()}{{}()}>)[[{()[]}<<>[]}]{[<>()][{}{}]}]]>{{[({<>[]})][([[]()]<{}<
[[[<<{({[{{<[[[]()]<(){}>]{([])<()[]>}>[[({}[])[[]<>]][<(){}>[()<>>]]}{<(<[]()>{{}<>})<(<>[])({}
<{[[<[[{[[(<<{(){}}({}<>)>({{}()}([][]))}([<[]{}>(<><>)]))<{{<[]<>>(()<>)}([[]()]({}[]))}({[<>{}
(<[<[{{([{(<[[()()]]{{<>()}{<><>}}>(<[()[]]{[][]]>({<><>}<<>[]>)))((<<()[]>>)[[<{}[]>]<[{}()]<{}<>>>])}[
{{{[<{[((<([<{(){}}{{}[]}>{[(){}][{}{}]}])<<[<<>[])(()())][((){})]>{({()[]}[[]{}])}>>){[({
{(((<(<{(<<((<[]()>))({({}<>)({}<>)})>>)}>)><[[<[<[[{(<><>)({}())}(({}<>){()[]})]]{{[{<>{}}
{{(<<(<([({(({[]{}}{{}[]})<(()())[()()]>)}<{{[{}<>](<>{})}{{{}()}{<>{}}})(({{}{}})(<[][]>{{}()}))>){[<[{<>()}
[(<{<([[[<({[{<><>}{<>{}}][{()<>}{<>()}]}<(<()[]><()<>>)>)><<([[{}{}]]([<>[]][[]<>]))(<<<>()>[()<>]>
({<{(<{[(<<(<([][])[(){}]>{({}<>)[{}[]]}){(({}[])[()()))(<[][]>(()<>))}>>)((<(<({}[]){[]()}>){({<>()}[{}
{<[([<<[(<{((<{}<>>)(<{}[]>{{}[]}))}(<[<[]()>([]())]{[{}]<<>[]>}><[{{}<>}[{}[]]](([]<>)<{}{}>)>)>){
[(({<([(<<[<<[[]<>]<()()>>{({})([]{})}>]<[{[<>()]{[]<>}}{{{}<>}<<>()>}]>>>((<{(<<><>>{[]{}})({<>[]}((){
{[[([[{<<[[[<[<>{}]{<>()}>({{}()}[()<>])]]({{<{}[]><<><>>}{<()<>>}}{{{[]()}<()<>>}<(<>[])(()[])>}
{<{<{{{{[[[((<{}{}>{{}{}})<{{}{}}{(){}}>)[[(()<>)]]]({{([]())[<>[]]}}[{<[]<>>((){})}([[]<>])])]
<[({({[[<(<({([]<>)[<>]}[[()[]]([])])<(<(){}>(()[]))[<<>()>[[]{}]]>>>><{[{<[{}{}][{}()]>{([]())[<><>]}}<{<<
[{<([<[(<({[<[<>]<{}[]>>{({}<>)[[]<>]}]{{{[]<>}(()[])}<(())>}}[({{[]()}{{}{}}}{{[]{}}[()[]]})])<{({{(){}}
{{([(([{{{((<[()[]][<>{}]>[<<>()>[[][]]])<{<{}>[(){}]}{({}())[()()]}>)({({<>[]}<[]()})<({}{})<<
(<(<{([<((<{<<{}<>>[()()]>({[]<>})}([[<>()]([]())])>{([{{}[]}(<>[])]{[[][]]{[][]}})}))>(<[{
[({{{(<[{{{<(<{}>)<{<>()}<(){}>>>[[{()()}(()<>)][[[][]]([][])]]}}}((({((<>{}){{}{}))[{{}<>
[<(([(<[{[<<({(){}})([<>[]){[]()})>>([[<()[]><{}>]])]<({[{()<>}<[]<>>][[<>()]]})>}]>)]{<{<(
([(({({{{[[{(<[][]><{}<>>)}[((<><>)(()<>))<{<><>}{<><>}>]]{(([{}<>][[]()])<<[][]>([]())>){<[(){}]>}}][(<<
({(<{<({{{<(<<[][]}>[{[]<>}{[]{}}])><(<(<>[])([][])>{({}<>)}){{<[]()>(()<>)}}>}(<<<<{}{}>[(){}]>[[()[]](
<[<((<{{{<<{({<>[]}){<[][]><[]<>>}}{[{[]()}[{}[]]][({}())]}>[{<[()<>]>[{()[]}((){})]}(({[]()}
[<({{[([([<{<[[]]<{}{}>>{([][])[()<>]]}[[<(){}><(){}>]]>((((<>{})[<>{}]){(()[])}){<[<>](()<>)>([[]<
<[[([[[(({{<(({}){[][]})[[<>[]]([]())]><[<{}{}><{}()>]>}<(<{<><>}{()[]}>([{}()]((){})))>}{{([{{}}{<>[
([[{<<<<(({(<([]{})<{}[]>>)}([[<{}()>)([[]{}]<<>()>)]{{{(){}}}[<()<>>{<><>}]}))<[{{<()()><{}{}>}}][{{<(){}><
{[{{(<{{{<([[{{}()}{{}{}}]]({<[][]>(<>[])}[[[][]]{<>[]}]))>}}}>)}}{<[<{[{[<[({[]}<[]{}>){(<>{})(<><>)
<{{([<{{{{<{{{()[]}{[]()}}{(())((){})}}{{(()[])[{}<>]}(<()<>>([][]))}>}}<[{({<()[]>({}[])}
{(<([(([{{{({[()()]<()>}[([]()}<<><>>])[{[<>[]]<<>>}{{[]<>}(<>())}]}}<{{[[{}[]]]}(<<<>()>>({[]()}<<>[]>)
<[{{{[{(<<(({<<>[]>{[]}}<{{}[]}<{}[]>>)[[([]())(<><>)][[[]()]([]{})]])[<{(()[])<()>}>([{{}{}}]{<(){}>(()())
[(<[({<{[[{<{[{}[]]{[]<>}}[<()<>>[[][]]]>(<[{}<>]({}())><[{}{}]{()()}>)}((<<<>[]>(<>{})>[(<>())[{}{
[[<{{<<[<<<<({{}<>}<{}[]>)((()[])[[]<>])><([<>[]]([]<>)){(<><>)}>>[({{()}{<>[]}}[([][])[{}[]]]){([{}()][<>{
{{{<({[([({<[[{}<>](()())]([<><>]({}[]))>}(({<<>()>[[]]}[<()>{<>{}}])))]<{({{(()())<<>>}}[({[]{}}[
(<<{(({{(<{{[<{}{}><<>()>]{{()[]}<<>{}>}}(<<{}<>><()>>{<()<>>([]{})})}<[{([]<>)<<>[]>}[<{}()><{}>]]>>
{<[[<{[{(<{<<(<>)<{}{}>>>[<<{}()>({}{})>]}<<[({}[]](()[])]{<[]<>>(())}>{<{()<>}({}<>)>{<()
[{((<<{<{[{[([()<>](()())){(<>())}}([[()<>][{}()]][({}[]){[]()}])}([<[()<>]([][])>[[[]{}]{()[
[<[<[<<[([<{[{()<>}({}())]<{{}<>}([]())>}(<<<>{}>[{}{}]>[[[]()]])>]<({({(){}}<[]{}>)([()<>](()))}[({[
[<[<[(((<(({{([][])}}<[[[][]]{(){}}>(<()[]>[[][]])>){([(()<>)<()()>]<[{}()]([]())>)<<{<>()}>([<>[]]{[]
{{<{<[{[{[(<([{}()]{[]<>})({<>})>(({{}{}}([]{}))))<[[({}{})<()[]>][{[]{}}{(){}}]]{([()<>])(<()[]><<>()>)}>]}
((({([{(<{<[[{{}<>}<{}()>]{([]<>)[{}[]]}]>(<([[]<>])((<>[]))>{<{[][]}(<>())>{{{}()}(()[])}})}>{[
<{(([(<<<(<({{[]<>}{<>[]}}{[[]{}][[]{}]})>[<<[{}<>](()<>)><(()<>)[{}()]>>(<{<>[]]({}[])>{(<>)<[]{}>})]){[<<[(
<{<{{<<<{((<([()()])(<(){}>{<>()})>)<([([]()){{}[]}]{{{}}<{}[]>})(<{()<>)>[<{}()>])>)(<(([()()](
(<[{(<(<[<<[((<>[]){<>[]})<(<>[]]({}{})>][[{[]{}}{()[]}]]>>[<{(<[][]>{{}{}})}<([()[]]<[]<>>)[{<><>}<{}{
({{<<({<<[<[<(<><>)<()<>>><[()<>]([][])>]>[{{<()<>><()<>>}}(<{{}()}><{{}{}}<{}{}>>)]]([[[{[
([[<<([[<([{([()[]](<>[]))<<<>[])({}[])>}[{[[][]]}]])[<[[({}{})<{}<>>]]([{{}<>}({}[])](([]())({}{})))>[[
<{<(<{({[({[[[()[]]{{}[]}][[<>[]>{<>[]}]]})]<{[{<[{}[]]{<>()}><([]<>){<>{}}>}{(<(){}>{<>{}})
<<<[<{(<{(({({<><>}{{}<>}){([][])([][])}}[<{(){}}<<>{}>>{([]<>)[()[]]}>)<{{{<><>}{<>{}}}{(
<[<<(({<<[<([<{}()><<><>]])<[[{}<>][<>{}]]<[{}<>](<>{})>>>](<(<[[]<>][(){}]>(<<>()>{<>})){[({}[]
<[[{<[([[[[{([()<>])<[[][]]{[]{}}>}](<[(<><>)]>({{(){}}<[]()>}))]]]{<({{(<<>()><(){}>){<[]<>>}}}[[
<{[[[(({{[{(([{}()][<>()]))[([[]{}]<{}[]>)([<>[]]{{}{}})]}[{[(()[])]((<>{}){()()})}<<{(){}}[[]<>
(<((<[[{((({((()<>)){{{}<>}(<><>)}}<((()[]))>)([{(<>[])([]<>)}[([][])(<>[])]]<<<<>{}>>[([][])[{}<>]>>))([<
[(((<[<{[([{(<<>[]>((){}))[[[]()][<>{}]])]<({<{}>{[][]}}{{{}<>}[<>[]]})<<{{}()}{(){}}>({<>{}}{{}[]
{((<[{[<<<[{<[(){}]({}{})>}<[<[]{}>({}<>)][{(){}}<<>{}>)>][<[[{}]({}[])]>]><[{{{{}[]}}({[]{}}(<>
[[<[(((({[[[({<>{}}{{}()})[<(){}>{<>}]]{(<<>{}>(()<>))([[]<>]<()<>>)}](<([[]{}]<()()>)>{<[()
[<<<[{[<{[(({([][]){<>}}))(<{([]())[{}()]}[[{}[]]{[]<>}]><{(<>())<[][]>}[([][]){{}()}]>)](
<(<{<<[([[<{[[<>()]<{}{}>][[(){}]<<><>>]}>]{(([[<>[]](<>{})>{<()[]><[][]>})[<[(){}]{[][]}><[<>()]([][]
<[([<<[{{[<<<{<>()}[<>()]>[<{}{}>[{}{}]]>[{[(){}]}([[]{}])]>]}}]>>{<([<(({({[][]}){([]()){{}{}}}}
[({{{((<{<{[<<{}{}>><<[]{}><<>{}>>][<{{}()}>]}[(<[[]]<<>[]>>[([]())<<>{}>]){<[{}[]]>((()<>)[[]{}
{[<[[(({([[<[(<>{})<<>{}>]{<()<>><{}[]>}>[[{{}<>}([]<>)][({}())([]())]]]])[[({{[[]<>][<>{}]}[[<>
(((<[[<<{{[[[([]{})<{}<>>]]{{[<>[]]<{}{}>}({<><>}{()()})}][[<<[]()>{<>[]}><(()())<()[]>>]{{([](
[<{<[[[<<{[{<({})([][])>[{[]()}]}<[{{}()}[<><>]]>][((<[]{}><{}()>)(<<>()>))([<<>()>((){})>)]}{[({(<>())<(
{([<[{{{<[{[({<>()}(<>{})}[{()()}[<><>]]]}<{{{<>[]}[{}[]]}({[]()}[{}()])}<({()()}<<><>>){{{}{}}
{{<[[<({([{(([[]<>][{}<>])){{({}{})<<>()>}}}[[{[<><>][{}{}]}<{[][]}([]())>]<<[()[]]{()<>}>{<{}[]}(()())}>]
<({<[([{[[<{<{[]{}}[<>]>}>([(<()[]>[[]<>])<{<>[]}>]{[(()())[{}]][<{}<>><{}>]})]]([{{{([]{})[(){}]}[<
[<<[{[{{[[{[[[()<>][()[]]](({}<>)[()<>])]<<(()[])>([<>[]]{()<>})>}([<[{}{}]>{({}<>){[][]}}][{[<>[]][<>]}])]]}
'.freeze

solution = Day10.new

puts 'Solution for Day 10:'
puts format('---> Part 1: %s', solution.solve_first(INPUT))
puts format('---> Part 2: %s', solution.solve_second(INPUT))
