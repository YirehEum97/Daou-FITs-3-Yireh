import streamlit as st

# Set page configuration
st.set_page_config(page_title="PROFOILO 전문가 상세 페이지", layout="wide")

# Header
st.title("PROFOILO")
st.markdown("---")

# Expert Info Section
col1, col2 = st.columns([1, 2])

with col1:
    # Placeholder for profile image
    st.image("./face.jpg", width=200)  # Placeholder image URL, replace with real path
    st.subheader("전문가 정보")
    st.markdown("**프로페셔널 III**")
    st.markdown("⭐ 4.98 (20)")

with col2:
    # Summary and reviews
    st.markdown("### 이미 구매한 포트폴리오예요")
    st.markdown("**가격 정보:** 5개 포트폴리오")
    st.button("환불 문의하기", key="refund_button")

st.markdown("---")

# Portfolio Description Section
st.subheader("포트폴리오 설명")
portfolio_descriptions = [
    "포트폴리오 1에 대한 설명입니다. 해당 서비스는 고객의 니즈를 반영하여 제작되었습니다.",
    "포트폴리오 2에 대한 설명입니다. 프로젝트 완료 후 고객 만족도가 매우 높았습니다.",
    "포트폴리오 3에 대한 설명입니다. 주요 기술 스택을 활용해 진행했습니다.",
    "포트폴리오 4에 대한 설명입니다. 주요 데이터 분석 및 시각화가 포함되었습니다."
]

for description in portfolio_descriptions:
    st.text(description)

# Footer with additional options
st.markdown("---")
st.markdown("**최근 100명의 사용자가 해당 전문가에게 조언을 받았어요!**")
st.button("돌아보기", key="back_button")
st.button("마이페이지", key="mypage_button")
