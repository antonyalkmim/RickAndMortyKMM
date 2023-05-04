import SwiftUI
import shared


extension shared.Character: Identifiable { }

struct ContentView: View {
    let viewModel = CharacterListViewModel()

    @State var state: CharacterListViewModel.State = CharacterListViewModel.StateEmpty()
    
	var body: some View {
        
        NavigationView {
            switch state {
            case is CharacterListViewModel.StateEmpty:
                Text("Lista vazia")
            case is CharacterListViewModel.StateItems:
                let successState = state as! CharacterListViewModel.StateItems
                
                List(successState.items) { character in
                    VStack(alignment: .leading){
                        Text(character.id).fontWeight(.bold)
                        Text(character.name)
                    }
                }
            default:
                Text("Deu ruim")
            }
        }
        .onAppear {
            Task {
                state = try await viewModel.loadCharacters()
            }
        }
            
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
