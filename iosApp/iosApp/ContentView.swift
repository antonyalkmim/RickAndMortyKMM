import SwiftUI
import shared

struct ContentView: View {
    let viewModel = CharacterListViewModel()

    @State var charactersName: String = ""
    
	var body: some View {
		Text(charactersName)
            .onAppear {
                Task {
                    charactersName = try await viewModel.loadCharactersNames()
                }
            }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
